package ro.ubb.catalog.core.service;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.ubb.catalog.core.model.Hotel;
import ro.ubb.catalog.core.model.PackageHoliday;
import ro.ubb.catalog.core.model.Pair;
import ro.ubb.catalog.core.model.exceptions.ValidatorException;
import ro.ubb.catalog.core.model.validators.HotelValidator;
import ro.ubb.catalog.core.repository.HotelRepository;
import ro.ubb.catalog.core.repository.PackageHolidayRepository;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class HotelService implements InterfaceHotelService {
    public static final Logger logger = LoggerFactory.getLogger(HotelService.class);

    @Autowired
    private HotelRepository hotelRepository;

    @Autowired
    private HotelValidator hotelValidator;

    @Autowired
    private PackageHolidayRepository packageHolidayRepository;

    @Override
    public List<Hotel> getHotels() {
        logger.trace("HotelService: getHotels -> method entered");
        List<Hotel> hotels = hotelRepository.findAll();
        logger.trace("HotelService: getHotels -> method finished: " + hotels.toString());

        return hotels;
    }

    @Override
    public void addHotel(Hotel hotel) throws ValidatorException {
        logger.trace("HotelService: addHotel -> method entered: " + hotel.toString());
        hotelValidator.validate(hotel);
        hotelRepository.save(hotel);
        logger.trace("HotelService: addHotel -> method finished");
    }

    @Override
    public void removeHotel(Long idHotel) throws ValidatorException {
        logger.trace("HotelService: removeHotel -> method entered: idHotel = " + idHotel);
        this.packageHolidayRepository.findAll().forEach((packageHoliday -> {
            if (packageHoliday.getHotelId().equals(idHotel)) {
                this.packageHolidayRepository.deleteById(packageHoliday.getId());
            }
        }));

        hotelRepository.findById(idHotel)
                .ifPresentOrElse(
                        (hotel) -> hotelRepository.deleteById(idHotel),
                        () -> {
                            throw new ValidatorException("The hotel does not exist!");
                        }
                );


        logger.trace("HotelService: removeHotel -> method finished");
    }

    @Override
    @Transactional
    public void updateHotel(Hotel hotel) throws ValidatorException {
        logger.trace("HotelService: updateHotel -> method entered: " + hotel.toString());

        hotelValidator.validate(hotel);
        hotelRepository.findById(hotel.getId())
                .ifPresentOrElse(
                        (updatedHotel) -> {
                            updatedHotel.setName(hotel.getName());
                            updatedHotel.setLocation(hotel.getLocation());
                            updatedHotel.setStars(hotel.getStars());
                            updatedHotel.setCapacity(hotel.getCapacity());
                        }
                        , () -> {
                            logger.trace("HotelService: updateHotel -> ValidatorException: hotel does not exist!");
                            throw new ValidatorException("Hotel does not exist!");
                        }
                );

        logger.trace("HotelService: updateHotel -> method finished");
    }

    @Override
    public List<Hotel> filterHotel(int stars) {
        logger.trace("HotelService: filterHotel -> method entered: stars = " + stars);

        List<Hotel> hotels = hotelRepository.findAllByStars(stars);

        logger.trace("HotelService: filterHotel -> method finished");
        return hotels;
    }

    @Override
    public List<Pair<Hotel, Long>> reportHotels() {
        logger.trace("HotelService: reportHotels -> method entered");

        List<Pair<Hotel, Long>> reportList = new ArrayList<>();



        getHotels().forEach(hotel -> {
            Iterable<PackageHoliday> packages = packageHolidayRepository.findAll();
            Set<PackageHoliday> allPackages = StreamSupport.stream(packages.spliterator(), false)
                    .collect(Collectors.toSet());



            long number = allPackages.stream()
                    .filter(packageHoliday -> packageHoliday.getHotelId().equals(hotel.getId()))
                    .count();



            reportList.add(new Pair<>(hotel, number));
        });



        reportList.sort(Comparator.comparing(Pair::getSecond));
        List<Pair<Hotel, Long>> returnedList = reportList.stream()
                .filter(item -> item.getSecond() > 0)
                .collect(Collectors.toList());

        logger.trace("HotelService: reportHotels -> method finished");
        return returnedList;
    }
}

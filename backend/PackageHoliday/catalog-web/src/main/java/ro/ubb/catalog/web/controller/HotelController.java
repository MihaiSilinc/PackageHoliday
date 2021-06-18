package ro.ubb.catalog.web.controller;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ro.ubb.catalog.core.model.Hotel;
import ro.ubb.catalog.core.model.Pair;
import ro.ubb.catalog.core.model.exceptions.ValidatorException;
import ro.ubb.catalog.core.service.InterfaceHotelService;
import ro.ubb.catalog.web.converter.HotelConverter;
import ro.ubb.catalog.web.dto.HotelDto;
import ro.ubb.catalog.web.dto.HotelsDto;


import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class HotelController {
    public static final Logger logger = LoggerFactory.getLogger(HotelController.class);

    @Autowired
    private InterfaceHotelService hotelService;

    @Autowired
    private HotelConverter hotelConverter;

    @RequestMapping(value = "/hotels")
    HotelsDto getHotels() {
        logger.trace("HotelController: getHotels -> method entered");

        List<Hotel> hotels = hotelService.getHotels();
        HotelsDto hotelsDto = new HotelsDto(hotelConverter.convertModelsToDtos(hotels));

        logger.trace("HotelController: getHotels -> method finished: " + hotelsDto.toString());

        return hotelsDto;
    }

    @RequestMapping(value = "/hotels", method = RequestMethod.POST)
    boolean addHotel(@RequestBody HotelDto hotelDto) throws ValidatorException, IOException {
        logger.trace("HotelController: addHotel -> method entered: " + hotelDto.toString());

        boolean toReturn = true;

        try {
            Hotel hotel = hotelConverter.convertDtoToModel(hotelDto);
            hotelService.addHotel(hotel);
        } catch (ValidatorException validatorException) {
            toReturn = false;
        } finally {
            logger.trace("HotelController: addHotel -> method finished");
        }

        return toReturn;
    }

    @RequestMapping(value = "/hotels/{id}", method = RequestMethod.DELETE)
    void removeHotel(@PathVariable Long id) throws ValidatorException, IOException {
        logger.trace("HotelController: removeHotel -> method entered: id = " + id);

        hotelService.removeHotel(id);

        logger.trace("HotelController: removeHotel -> method finished");
    }

    @RequestMapping(value = "/hotels/{id}", method = RequestMethod.PUT)
    void updateHotel(@PathVariable Long id, @RequestBody HotelDto hotelDto) throws ValidatorException, IOException {
        logger.trace("HotelController: updateHotel -> method entered: " + hotelDto.toString());

        Hotel hotel = hotelConverter.convertDtoToModel(hotelDto);
        hotel.setId(id);
        hotelService.updateHotel(hotel);

        logger.trace("HotelController: updateHotel -> method finished");
    }

    @RequestMapping(value = "/hotels-filter/{stars}")
    HotelsDto filterHotel(@PathVariable int stars) {
        logger.trace("HotelController: filterHotel -> method entered: stars = " + stars);

        List<Hotel> hotels = hotelService.filterHotel(stars);
        HotelsDto hotelsDto = new HotelsDto(hotelConverter.convertModelsToDtos(hotels));

        logger.trace("HotelController: filterHotel -> method finished");

        return hotelsDto;
    }

    @RequestMapping(value = "/hotels-report", method = RequestMethod.POST)
    List<Pair<HotelDto, Long>> reportHotels() {
        logger.trace("HotelController: reportHotels -> method entered");

        List<Pair<Hotel, Long>> reportList = hotelService.reportHotels();

        List<Pair<HotelDto, Long>> hotelsDto = reportList
                .stream()
                .map(pair -> new Pair<>(hotelConverter.convertModelToDto(pair.getFirst()), pair.getSecond()))
                .collect(Collectors.toList());

        logger.trace("HotelController: reportHotels -> method finished");

        return hotelsDto;
    }
}

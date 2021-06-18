package ro.ubb.catalog.core.service;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.ubb.catalog.core.model.PackageHoliday;
import ro.ubb.catalog.core.model.exceptions.ValidatorException;
import ro.ubb.catalog.core.model.validators.PackageHolidayValidator;
import ro.ubb.catalog.core.repository.CustomerRepository;
import ro.ubb.catalog.core.repository.HotelRepository;
import ro.ubb.catalog.core.repository.PackageHolidayRepository;

import java.io.IOException;
import java.util.List;

@Service
public class PackageHolidayService implements InterfacePackageHolidayService{
    public static final Logger logger = LoggerFactory.getLogger(PackageHolidayService.class);

    @Autowired
    PackageHolidayRepository packageHolidayRepository;

    @Autowired
    PackageHolidayValidator packageHolidayValidator;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    HotelRepository hotelRepository;

    @Override
    public List<PackageHoliday> getPackages() {
        logger.info("PackageHolidayService: getPackages");
        return packageHolidayRepository.findAll();
    }

    @Override
    public void addPackageHoliday(PackageHoliday packageHoliday) throws ValidatorException {
        logger.info("PackageHolidayService: addPackage");
        if (!customerRepository.existsById(packageHoliday.getCustomerId())){
            logger.info("PackageHolidayService: customer doesnt exist");
            throw new ValidatorException("Customer doesnt exist");
        }
        if (!hotelRepository.existsById(packageHoliday.getHotelId())){
            logger.info("PackageHolidayService: hotel doesnt exist");
            throw new ValidatorException("Hotel doesnt exist");
        }
        packageHolidayValidator.validate(packageHoliday);
        packageHolidayRepository.save(packageHoliday);
        logger.info("PackageHolidayService: addPackage");
    }

    @Override
    public void removePackageHoliday(Long id) throws ValidatorException {
        logger.info("PackageHolidayService: removePackage");
        packageHolidayRepository.deleteById(id);
        logger.info("PackageHolidayService: removePackage");
    }

    @Override
    public void updatePackageHoliday(PackageHoliday packageHoliday) throws ValidatorException, IOException {
        logger.info("PackageHolidayService: updatePackage");
        packageHolidayValidator.validate(packageHoliday);
        packageHolidayRepository.findById(packageHoliday.getId())
                .ifPresentOrElse(
                        (updatedPackage) -> {
                            updatedPackage.setStartDate(packageHoliday.getStartDate());
                            updatedPackage.setEndDate(packageHoliday.getEndDate());
                            updatedPackage.setPrice(packageHoliday.getPrice());
                            updatedPackage.setCustomerId(packageHoliday.getCustomerId());
                            updatedPackage.setHotelId(packageHoliday.getHotelId());
                        }
                        , () -> {
                            logger.trace("PackageHolidayService: updatePackage -> ValidatorException: Package does not exist!");
                            throw new ValidatorException("Package does not exist!");
                        }
                );
        logger.info("PackageHolidayService: updatePackage");
    }


    @Override
    public List<PackageHoliday> filterByPrice(int price) {
        logger.info("PackageHolidayService: filterByPrice");
        return packageHolidayRepository.findAllByPrice(price);
    }
}

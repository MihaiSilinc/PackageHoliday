package ro.ubb.catalog.web.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import ro.ubb.catalog.core.model.PackageHoliday;
import ro.ubb.catalog.core.model.exceptions.ValidatorException;
import ro.ubb.catalog.core.service.InterfacePackageHolidayService;
import ro.ubb.catalog.web.converter.PackageHolidayConverter;
import ro.ubb.catalog.web.dto.PackageHolidayDto;
import ro.ubb.catalog.web.dto.PackagesHolidayDto;


import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class PackageHolidayController {
    public static final Logger logger = LoggerFactory.getLogger(PackageHolidayController.class);

    @Autowired
    private InterfacePackageHolidayService packageHolidayService;

    @Autowired
    private PackageHolidayConverter packageHolidayConverter;

    @RequestMapping(value = "/packages")
    PackagesHolidayDto getPackages() {
        List<PackageHoliday> packages = packageHolidayService.getPackages();
        PackagesHolidayDto packagesHolidayDto = new PackagesHolidayDto(packageHolidayConverter.convertModelsToDtos(packages));

        logger.trace("HotelController: getHotels -> method finished: " + packagesHolidayDto.toString());

        return packagesHolidayDto;
    }

    @RequestMapping(value = "/packages", method = RequestMethod.POST)
    boolean addPackage(@RequestBody PackageHolidayDto packageHolidayDto) throws ValidatorException, IOException {
        logger.trace("HotelController: addHotel -> method entered: " + packageHolidayDto.toString());

        boolean toReturn = true;

        try {
            PackageHoliday packageHoliday = packageHolidayConverter.convertDtoToModel(packageHolidayDto);
            packageHolidayService.addPackageHoliday(packageHoliday);
        } catch (ValidatorException validatorException) {
            toReturn = false;
        } finally {
            logger.trace("PackageHolidayController: addPackage -> method finished");
        }

        return toReturn;
    }

    @RequestMapping(value = "/packages/{id}", method = RequestMethod.DELETE)
    void removePackage(@PathVariable Long id) throws ValidatorException {
        logger.trace("packageholiday: removeHotel -> method entered: id = " + id);

        packageHolidayService.removePackageHoliday(id);

        logger.trace("packageholiday: removeHotel -> method finished");
    }

    @RequestMapping(value = "/packages/{id}", method = RequestMethod.PUT)
    void updatePackage(@PathVariable Long id, @RequestBody PackageHolidayDto packageHolidayDto) throws ValidatorException, IOException {
        logger.trace("HotelController: updateHotel -> method entered: " + packageHolidayDto.toString());

        PackageHoliday packageHoliday = packageHolidayConverter.convertDtoToModel(packageHolidayDto);
        packageHolidayService.updatePackageHoliday(packageHoliday);

        logger.trace("HotelController: updateHotel -> method finished");
    }
}

package ro.ubb.catalog.core.service;



import ro.ubb.catalog.core.model.PackageHoliday;
import ro.ubb.catalog.core.model.exceptions.ValidatorException;

import java.io.IOException;
import java.util.List;

public interface InterfacePackageHolidayService {
    List<PackageHoliday> getPackages();

    void addPackageHoliday(PackageHoliday packageHoliday) throws ValidatorException;

    void removePackageHoliday(Long id) throws ValidatorException;

    void updatePackageHoliday(PackageHoliday packageHoliday) throws ValidatorException, IOException;

    List<PackageHoliday> filterByPrice(int price);
}

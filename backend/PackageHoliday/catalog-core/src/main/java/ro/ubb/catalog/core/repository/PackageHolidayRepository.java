package ro.ubb.catalog.core.repository;


import ro.ubb.catalog.core.model.PackageHoliday;

import java.util.List;

public interface PackageHolidayRepository extends InterfaceRepository<PackageHoliday, Long> {
    List<PackageHoliday> findAllByPrice(int price);
}

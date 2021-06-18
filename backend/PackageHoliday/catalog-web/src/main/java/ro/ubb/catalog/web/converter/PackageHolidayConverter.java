package ro.ubb.catalog.web.converter;


import org.springframework.stereotype.Component;
import ro.ubb.catalog.core.model.PackageHoliday;
import ro.ubb.catalog.web.dto.PackageHolidayDto;


@Component
public class PackageHolidayConverter extends BaseConverter<PackageHoliday, PackageHolidayDto> {
    @Override
    public PackageHoliday convertDtoToModel(PackageHolidayDto dto) {
        PackageHoliday packageHoliday = new PackageHoliday();
        packageHoliday.setId(dto.getId());
        packageHoliday.setCustomerId(dto.getCustomerId());
        packageHoliday.setHotelId(dto.getHotelId());
        packageHoliday.setStartDate(dto.getStartDate());
        packageHoliday.setEndDate(dto.getEndDate());
        packageHoliday.setPrice(dto.getPrice());

        return packageHoliday;
    }

    @Override
    public PackageHolidayDto convertModelToDto(PackageHoliday packageHoliday) {
        PackageHolidayDto packageHolidayDto = new PackageHolidayDto();
        packageHolidayDto.setId(packageHoliday.getId());
        packageHolidayDto.setCustomerId(packageHoliday.getCustomerId());
        packageHolidayDto.setHotelId(packageHoliday.getHotelId());
        packageHolidayDto.setStartDate(packageHoliday.getStartDate());
        packageHolidayDto.setEndDate(packageHoliday.getEndDate());
        packageHolidayDto.setPrice(packageHoliday.getPrice());

        return packageHolidayDto;
    }
}
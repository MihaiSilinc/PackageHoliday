package ro.ubb.catalog.core.model.validators;


import ro.ubb.catalog.core.model.PackageHoliday;
import ro.ubb.catalog.core.model.exceptions.ValidatorException;

public class PackageHolidayValidator implements Validator<PackageHoliday> {
    @Override
    public void validate(PackageHoliday entity) throws ValidatorException {
            if (entity.getPrice() < 0) {
                throw new ValidatorException("Price should be greater than 0");
            }
    }
}

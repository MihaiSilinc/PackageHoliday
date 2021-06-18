package ro.ubb.catalog.core.model.exceptions;

public class PackageHolidayException extends RuntimeException {
    public PackageHolidayException(String message){
        super(message);
    }

    public PackageHolidayException(String message, Throwable cause) {
        super(message, cause);
    }

    public PackageHolidayException(Throwable cause) {
        super(cause);
    }
}

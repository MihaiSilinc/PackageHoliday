package ro.ubb.catalog.core.model.exceptions;

public class StringNotConvertibleToDateException extends Exception{
    public StringNotConvertibleToDateException(String errorMessage) {
        super(errorMessage);
    }
}

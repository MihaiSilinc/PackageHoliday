package ro.ubb.catalog.core.model.validators;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ro.ubb.catalog.core.model.Customer;
import ro.ubb.catalog.core.model.exceptions.ValidatorException;

public class CustomerValidator implements Validator<Customer> {
    public static final Logger logger = LoggerFactory.getLogger(CustomerValidator.class);

    @Override
    public void validate(Customer customer) throws ValidatorException {
        if(customer.getName().isEmpty()) {
            logger.trace("CustomerValidator.validate -> Customer name must not be empty!");
            throw new ValidatorException("Customer name must not be empty!");
        }

        if(customer.getAge() < 18) {
            logger.trace("CustomerValidator.validate -> Customer must be at least 18 years old!");
            System.out.println("CustomerValidator.validate -> Customer must be at least 18 years old!");
            throw new ValidatorException("Customer must be at least 18 years old!");
        }
    }
}

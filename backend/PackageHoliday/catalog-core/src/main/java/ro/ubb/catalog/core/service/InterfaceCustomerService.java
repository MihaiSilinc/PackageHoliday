package ro.ubb.catalog.core.service;



import ro.ubb.catalog.core.model.Customer;
import ro.ubb.catalog.core.model.Pair;
import ro.ubb.catalog.core.model.exceptions.ValidatorException;

import java.io.IOException;
import java.util.List;

public interface InterfaceCustomerService {
    List<Customer> getCustomers();

    void addCustomer(Customer customer) throws ValidatorException, IOException;

    List<Customer>  getCustomersByName(String name) throws ValidatorException, IOException;

    void removeCustomer(Long idCustomer) throws ValidatorException, IOException;

    void updateCustomer(Customer customer) throws ValidatorException, IOException;

    List<Customer> filterCustomer(String name);

    List<Pair<Customer, Integer>> reportCustomers();
}

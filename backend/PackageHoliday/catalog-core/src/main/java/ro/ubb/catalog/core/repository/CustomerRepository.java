package ro.ubb.catalog.core.repository;



import ro.ubb.catalog.core.model.Customer;

import java.util.List;

public interface CustomerRepository extends InterfaceRepository<Customer, Long> {
    List<Customer> findAllByNameContains(String name);
}

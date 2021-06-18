package ro.ubb.catalog.core.service;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.ubb.catalog.core.model.Customer;
import ro.ubb.catalog.core.model.PackageHoliday;
import ro.ubb.catalog.core.model.Pair;
import ro.ubb.catalog.core.model.exceptions.ValidatorException;
import ro.ubb.catalog.core.model.validators.CustomerValidator;
import ro.ubb.catalog.core.repository.CustomerRepository;
import ro.ubb.catalog.core.repository.PackageHolidayRepository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class CustomerService implements InterfaceCustomerService {
    public static final Logger logger = LoggerFactory.getLogger(CustomerService.class);

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private CustomerValidator customerValidator;

    @Autowired
    private PackageHolidayRepository packageHolidayRepository;


    @Override
    public List<Customer> getCustomers() {
        logger.trace("CustomerService: getCustomers -> method entered");
        List<Customer> customers = customerRepository.findAll();
        logger.trace("CustomerService: getCustomers -> method finished: " + customers.toString());

        return customers;
    }

    @Override
    public List<Customer> getCustomersByName(String name) {
        logger.trace("getCustomerByName - method entered");
        var toReturn = customerRepository.findAllByNameContains(name);
        logger.trace("getCatsByBreed - method exited");
        return toReturn;
    }

    @Override
    public void addCustomer(Customer customer) throws ValidatorException {
        logger.trace("CustomerService: addCustomer -> method entered: " + customer.toString());
        customerValidator.validate(customer);
        customerRepository.save(customer);
        logger.trace("CustomerService: addCustomer -> method finished");
    }

    @Override
    public void removeCustomer(Long idCustomer) throws ValidatorException {
        logger.trace("CustomerService: removeCustomer -> method entered: idCustomer = " + idCustomer);

        this.packageHolidayRepository.findAll().forEach((packageHoliday -> {
            if (packageHoliday.getCustomerId().equals(idCustomer)) {
                this.packageHolidayRepository.deleteById(packageHoliday.getId());
            }
        }));

        customerRepository.findById(idCustomer)
                .ifPresentOrElse(
                        (client) -> customerRepository.deleteById(idCustomer),
                        () -> {
                            throw new ValidatorException("The client does not exist!");
                        }
                );

        logger.trace("CustomerService: removeCustomer -> method finished");
    }

    @Override
    @Transactional
    public void updateCustomer(Customer customer) throws ValidatorException {
        logger.trace("CustomerService: updateCustomer -> method entered: " + customer.toString());

        customerValidator.validate(customer);
        customerRepository.findById(customer.getId())
                .ifPresentOrElse(
                        (updatedCustomer) -> {
                            updatedCustomer.setName(customer.getName());
                            updatedCustomer.setAge(customer.getAge());
                        }
                        , () -> {
                            logger.trace("CustomerService: updateCustomer -> ValidatorException: Customer does not exist!");
                            throw new ValidatorException("Customer does not exist!");
                        }
                );

        logger.trace("CustomerService: updateCustomer -> method finished");
    }

    @Override
    public List<Customer> filterCustomer(String name) {
        logger.trace("CustomerService: filterCustomer -> method entered: name = " + name);

        List<Customer> customers = customerRepository.findAllByNameContains(name);

        logger.trace("CustomerService: filterCustomer -> method finished");
        return customers;
    }

    @Override
    public List<Pair<Customer, Integer>> reportCustomers() {
        logger.trace("CustomerService: reportCustomers -> method entered");

        List<Pair<Customer, Integer>> reportList = new ArrayList<>();

        getCustomers().forEach(client -> {
            Iterable<PackageHoliday> packages = packageHolidayRepository.findAll();
            Set<PackageHoliday> allPackages = StreamSupport.stream(packages.spliterator(), false)
                    .collect(Collectors.toSet());

            int money = allPackages.stream()
                    .filter(packageHoliday -> packageHoliday.getCustomerId().equals(client.getId()))
                    .map(PackageHoliday::getPrice)
                    .reduce(0, Integer::sum);
            reportList.add(new Pair<>(client, money));
        });

        reportList.sort(Comparator.comparing(Pair::getSecond));
        List<Pair<Customer, Integer>> returnedList = reportList.stream()
                .filter(item -> item.getSecond() > 0)
                .collect(Collectors.toList());

        logger.trace("CustomerService: reportCustomers -> method finished");
        return returnedList;
    }
}

package ro.ubb.catalog.web.controller;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ro.ubb.catalog.core.model.Customer;
import ro.ubb.catalog.core.model.Pair;
import ro.ubb.catalog.core.model.exceptions.ValidatorException;
import ro.ubb.catalog.core.service.InterfaceCustomerService;
import ro.ubb.catalog.web.converter.CustomerConverter;
import ro.ubb.catalog.web.dto.CustomerDto;
import ro.ubb.catalog.web.dto.CustomersDto;


import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class CustomerController {
    public static final Logger logger = LoggerFactory.getLogger(CustomerController.class);

    @Autowired
    private InterfaceCustomerService customerService;

    @Autowired
    private CustomerConverter customerConverter;

    @RequestMapping(value = "/customers")
    CustomersDto getCustomers() {
        logger.trace("CustomerController: getCustomers -> method entered");

        List<Customer> customers = customerService.getCustomers();
        CustomersDto customersDto = new CustomersDto(customerConverter.convertModelsToDtos(customers));

        logger.trace("CustomerController: getCustomers -> method finished: " + customersDto.toString());

        return customersDto;
    }

    @RequestMapping(value = "/customers/{name}")
    CustomersDto getCustomersByName(@PathVariable String name) throws IOException {
        logger.trace("getCustomerByName - method entered - name: " + name);
        List<Customer> customers = customerService.getCustomersByName(name);
        CustomersDto customersDTO = new CustomersDto(customerConverter.convertModelsToDtos(customers));
        logger.trace("getAllCustomers: " + customers);
        return customersDTO;
    }

    @RequestMapping(value = "/customers", method = RequestMethod.POST)
    boolean addCustomer(@RequestBody CustomerDto customerDto) throws ValidatorException, IOException {
        logger.trace("CustomerController: addCustomer -> method entered: " + customerDto.toString());

        boolean toReturn = true;

        try {
            Customer customer = customerConverter.convertDtoToModel(customerDto);
            customerService.addCustomer(customer);
        } catch (ValidatorException validatorException) {
            toReturn = false;
        } finally {
            logger.trace("CustomerController: addCustomer -> method finished");
        }

        return toReturn;
    }

    @RequestMapping(value = "/customers/{id}", method = RequestMethod.DELETE)
    void removeCustomer(@PathVariable Long id) throws ValidatorException, IOException {

        logger.trace("CustomerController: removeCustomer -> method entered: id = " + id);

        customerService.removeCustomer(id);

        logger.trace("CustomerController: removeCustomer -> method finished");
    }

    @RequestMapping(value = "/customers/{id}", method = RequestMethod.PUT)
    void updateCustomer(@PathVariable Long id, @RequestBody CustomerDto customerDto) throws ValidatorException, IOException {
        logger.trace("CustomerController: updateCustomer -> method entered: " + customerDto.toString());

        Customer customer = customerConverter.convertDtoToModel(customerDto);
        customer.setId(id);
        customerService.updateCustomer(customer);

        logger.trace("CustomerController: updateCustomer -> method finished");
    }

    @RequestMapping(value = "/customers-filter/{name}")
    CustomersDto filterCustomer(@PathVariable String name) {
        logger.trace("CustomerController: filterCustomer -> method entered: name = " + name);

        List<Customer> customers = customerService.filterCustomer(name);
        CustomersDto customersDto = new CustomersDto(customerConverter.convertModelsToDtos(customers));

        logger.trace("CustomerController: filterCustomer -> method finished");

        return customersDto;
    }

    @RequestMapping(value = "/customers-report")
    List<Pair<CustomerDto, Integer>> reportCustomers() {
        logger.trace("CustomerController: reportCustomers -> method entered");

        List<Pair<Customer, Integer>> reportList = customerService.reportCustomers();

        List<Pair<CustomerDto, Integer>> customersDto = reportList
                .stream()
                .map(pair -> new Pair<>(customerConverter.convertModelToDto(pair.getFirst()), pair.getSecond()))
                .collect(Collectors.toList());

        logger.trace("CustomerController: reportCustomers -> method finished");

        return customersDto;
    }
}

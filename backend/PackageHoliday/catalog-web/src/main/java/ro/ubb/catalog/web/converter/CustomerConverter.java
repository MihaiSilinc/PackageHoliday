package ro.ubb.catalog.web.converter;


import org.springframework.stereotype.Component;
import ro.ubb.catalog.core.model.Customer;
import ro.ubb.catalog.web.dto.CustomerDto;


@Component
public class CustomerConverter extends BaseConverter<Customer, CustomerDto> {
    @Override
    public Customer convertDtoToModel(CustomerDto dto) {
        Customer model = new Customer();
        model.setId(dto.getId());
        model.setName(dto.getName());
        model.setAge(dto.getAge());
        return model;
    }

    @Override
    public CustomerDto convertModelToDto(Customer customer) {
        CustomerDto dto = new CustomerDto();
        dto.setId(customer.getId());
        dto.setName(customer.getName());
        dto.setAge(customer.getAge());
        return dto;
    }
}

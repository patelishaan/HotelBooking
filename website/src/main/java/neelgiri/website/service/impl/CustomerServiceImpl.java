package neelgiri.website.service.impl;

import lombok.RequiredArgsConstructor;
import neelgiri.website.dto.CustomerRequestDto;
import neelgiri.website.dto.CustomerResponseDto;
import neelgiri.website.entity.Customer;
import neelgiri.website.repository.CustomerRepository;
import neelgiri.website.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    CustomerRepository customerRepository;
    @Override
    public CustomerResponseDto createCustomer(CustomerRequestDto customerRequestDto){
        //dto->entity
        Customer customer = Customer.builder()
                .firstName(customerRequestDto.getFirstName())
                .lastName(customerRequestDto.getLastName())
                .email(customerRequestDto.getEmail())
                .phoneNo(customerRequestDto.getPhoneNo())

                .build();

        //save to DB
        Customer saved = customerRepository.save(customer);

        //entity to response dto
        CustomerResponseDto result = CustomerResponseDto.builder()
                .firstName(saved.getFirstName())
                .lastName(saved.getLastName())
                .email(saved.getEmail())
                .phoneNo(saved.getPhoneNo())
                .customerId(saved.getCustomerId())
                .build();
        return result;
    }
}

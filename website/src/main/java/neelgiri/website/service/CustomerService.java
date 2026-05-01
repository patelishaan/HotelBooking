package neelgiri.website.service;

import neelgiri.website.dto.CustomerRequestDto;
import neelgiri.website.dto.CustomerResponseDto;

public interface CustomerService {
    CustomerResponseDto createCustomer(CustomerRequestDto customerRequestDto);
}

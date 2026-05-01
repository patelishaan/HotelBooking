package neelgiri.website.controller;


import lombok.RequiredArgsConstructor;
import neelgiri.website.dto.CustomerRequestDto;
import neelgiri.website.dto.CustomerResponseDto;
import neelgiri.website.service.CustomerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/customer")
public class CustomerController {
    private final CustomerService customerService;
    @PostMapping("/createCustomer")
    public ResponseEntity<CustomerResponseDto> createBooking (@RequestBody CustomerRequestDto customerRequestDto){
        CustomerResponseDto response = customerService.createCustomer(customerRequestDto);
        return ResponseEntity.ok(response);

    }
}

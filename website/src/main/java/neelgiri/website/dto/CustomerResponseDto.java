package neelgiri.website.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CustomerResponseDto {
    private String firstName;
    private String lastName;
    private Long customerId;
    private Long roomNo;
    private Long phoneNo;
    private String email;
}

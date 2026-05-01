package neelgiri.website.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerRequestDto {
    private String firstName;
    private String lastName;
    private String email;
    private Long phoneNo;
    private Long roomNo;
}

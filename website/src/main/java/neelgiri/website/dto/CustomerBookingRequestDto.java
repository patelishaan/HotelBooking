package neelgiri.website.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CustomerBookingRequestDto {
    //private Long bookingId;

    //private Long customerId;
    private String firstName;
    private String lastName;
    private String email;
    private Long phoneNo;
    private Long roomNo;
    private LocalDate checkIn;
    private LocalDate checkOut;
}

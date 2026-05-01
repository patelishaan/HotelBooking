package neelgiri.website.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import neelgiri.website.constants.BookingStatus;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AdminBookingResponseDto {
    private Long bookingId;
    private Long roomNo;
    private String firstName;
    private String lastName;
    private String email;
    private Long phoneNo;
    private LocalDate checkIn;
    private LocalDate checkOut;
    private BookingStatus bookingStatus;
}

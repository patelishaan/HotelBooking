package neelgiri.website.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CustomerBookingResponseDto {
    private Long bookingId;
    private String emailId;
    private Long roomNo;
}

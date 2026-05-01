package neelgiri.website.service;

import neelgiri.website.dto.AdminBookingResponseDto;
import neelgiri.website.dto.CustomerBookingRequestDto;
import neelgiri.website.dto.CustomerBookingResponseDto;

import java.util.List;

public interface BookingService {
    CustomerBookingResponseDto createBooking(CustomerBookingRequestDto customerBookingRequestDto);
    void removeBooking(Long BookingId);
    List<AdminBookingResponseDto> getAllBookings();
    List<AdminBookingResponseDto> getConfirmedBookings();
}

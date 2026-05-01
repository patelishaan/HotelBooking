package neelgiri.website.controller;

import lombok.RequiredArgsConstructor;
import neelgiri.website.dto.AdminBookingResponseDto;
import neelgiri.website.dto.CustomerBookingRequestDto;
import neelgiri.website.dto.CustomerBookingResponseDto;
import neelgiri.website.entity.Booking;
import neelgiri.website.service.BookingService;
import neelgiri.website.service.EmailService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequiredArgsConstructor
@RequestMapping("/booking")
public class bookingController {
    private final BookingService bookingService;
    private final EmailService emailService;
    @PostMapping("/createBooking")
    public ResponseEntity<CustomerBookingResponseDto> createBooking(@RequestBody CustomerBookingRequestDto customerBookingRequestDto){
        CustomerBookingResponseDto result = bookingService.createBooking(customerBookingRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @DeleteMapping("/deleteBooking/{id}")
    public String removeBooking(@PathVariable Long id){
        bookingService.removeBooking(id);
        return "Booking cancelled successfully";
    }

    @GetMapping("/test-mail")
    public String testMail() {
        emailService.sendBookingConfirmation("ishaanpatel29976@gmail.com", 123L, 101L);
        return "Mail sent";
    }
    @GetMapping("/all")
    public ResponseEntity<List<AdminBookingResponseDto>> getAllBookings() {
        return ResponseEntity.ok(bookingService.getAllBookings());
    }

    // Returns only CONFIRMED bookings (the special admin table)
    @GetMapping("/confirmed")
    public ResponseEntity<List<AdminBookingResponseDto>> getConfirmedBookings() {
        return ResponseEntity.ok(bookingService.getConfirmedBookings());
    }

}

package neelgiri.website.service.impl;

import lombok.RequiredArgsConstructor;
import neelgiri.website.constants.BookingStatus;
import neelgiri.website.constants.RoomStatus;
import neelgiri.website.dto.AdminBookingResponseDto;
import neelgiri.website.dto.CustomerBookingRequestDto;
import neelgiri.website.dto.CustomerBookingResponseDto;
import neelgiri.website.entity.Booking;
import neelgiri.website.entity.Customer;
import neelgiri.website.entity.Room;
import neelgiri.website.repository.BookingRepository;
import neelgiri.website.repository.CustomerRepository;
import neelgiri.website.repository.RoomRepository;
import neelgiri.website.service.BookingService;
import neelgiri.website.service.EmailService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {
    private final CustomerRepository customerRepository;
    private final BookingRepository bookingRepository;
    private final RoomRepository roomRepository;
    private final EmailService emailService;
    public CustomerBookingResponseDto createBooking(CustomerBookingRequestDto customerBookingRequestDto){
        //find or create customer
        Customer customer = customerRepository.findByEmail(customerBookingRequestDto.getEmail())
                .orElseGet(()->customerRepository.save(
                        Customer.builder()
                                .firstName(customerBookingRequestDto.getFirstName())
                                .lastName(customerBookingRequestDto.getLastName())
                                .email(customerBookingRequestDto.getEmail())
                                .phoneNo(customerBookingRequestDto.getPhoneNo())
                                .build()
                ));

        //find room by room number
        Room room = roomRepository.findByRoomNo(customerBookingRequestDto.getRoomNo());

        //create booking
        Booking booking = Booking.builder()
                .customer(customer)
                .room(room)

                .checkIn(customerBookingRequestDto.getCheckIn())
                .checkOut(customerBookingRequestDto.getCheckOut())
                .bookingStatus(BookingStatus.CONFIRMED)
                .build();

        room.setRoomStatus(RoomStatus.BOOKED);
        roomRepository.save(room);

        Booking savedBooking = bookingRepository.save(booking);

        // send email
        try {
            emailService.sendBookingConfirmation(
                    savedBooking.getCustomer().getEmail(),
                    savedBooking.getBookingId(),
                    savedBooking.getRoom().getRoomNo()
            );
        } catch (Exception e) {
            System.out.println("Email failed: " + e.getMessage());
        }

        return CustomerBookingResponseDto.builder()
                .bookingId(savedBooking.getBookingId())
                .emailId(savedBooking.getCustomer().getEmail())
                .roomNo(savedBooking.getRoom().getRoomNo())
                .build();


    }

    @Override
    public void removeBooking(Long BookingId) {
        Booking booking = bookingRepository.findById(BookingId).orElseThrow(()->new RuntimeException("Boooking not found"));
        Room room = booking.getRoom();
        //free room
        room.setRoomStatus(RoomStatus.UNBOOKED);
        roomRepository.save(room);
        //delete booking
        bookingRepository.delete(booking);
    }

    @Override
    public List<AdminBookingResponseDto> getAllBookings() {
        return bookingRepository.findAll()
                .stream()
                .map(booking -> AdminBookingResponseDto.builder()
                        .bookingId(booking.getBookingId())
                        .roomNo(booking.getRoom().getRoomNo())
                        .firstName(booking.getCustomer().getFirstName())
                        .lastName(booking.getCustomer().getLastName())
                        .email(booking.getCustomer().getEmail())
                        .phoneNo(booking.getCustomer().getPhoneNo())
                        .checkIn(booking.getCheckIn())
                        .checkOut(booking.getCheckOut())
                        .bookingStatus(booking.getBookingStatus())
                        .build()
                )
                .toList();
    }

    @Override
    public List<AdminBookingResponseDto> getConfirmedBookings() {
        return bookingRepository.findAll()
                .stream()
                .filter(booking -> booking.getBookingStatus() == BookingStatus.CONFIRMED)
                .map(booking -> AdminBookingResponseDto.builder()
                        .bookingId(booking.getBookingId())
                        .roomNo(booking.getRoom().getRoomNo())
                        .firstName(booking.getCustomer().getFirstName())
                        .lastName(booking.getCustomer().getLastName())
                        .email(booking.getCustomer().getEmail())
                        .phoneNo(booking.getCustomer().getPhoneNo())
                        .checkIn(booking.getCheckIn())
                        .checkOut(booking.getCheckOut())
                        .bookingStatus(booking.getBookingStatus())
                        .build()
                )
                .toList();
    }

}

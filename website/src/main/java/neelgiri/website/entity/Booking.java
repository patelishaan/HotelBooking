package neelgiri.website.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import neelgiri.website.constants.BookingStatus;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookingId;

    private LocalDate checkIn;
    private LocalDate checkOut;

    @ManyToOne
    private Customer customer; // FK to customer

    @ManyToOne
    private Room room;

    @Enumerated(EnumType.STRING)
    private BookingStatus bookingStatus;
}

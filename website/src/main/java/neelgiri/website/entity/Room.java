package neelgiri.website.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import neelgiri.website.constants.RoomStatus;
import neelgiri.website.constants.RoomType;

import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long roomId;
    private Long roomNo;

    @Enumerated(EnumType.STRING)
    private RoomType roomType;

    private Double pricePerNight;

    @Enumerated(EnumType.STRING)
    private RoomStatus roomStatus;

    @Column(name = "hold_time")
    private LocalDateTime holdTime;


}

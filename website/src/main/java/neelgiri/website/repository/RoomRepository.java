package neelgiri.website.repository;

import neelgiri.website.constants.RoomStatus;
import neelgiri.website.constants.RoomType;
import neelgiri.website.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface RoomRepository extends JpaRepository<Room, Long> {
    Room findByRoomNo(Long roomNo);

    @Query("SELECT r FROM Room r WHERE r.roomType = :type AND r.roomStatus = :status")
    List<Room> findRoomByFields(@Param("type") RoomType type,
                                @Param("status") RoomStatus status);
}

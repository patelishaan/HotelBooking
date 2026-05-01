package neelgiri.website.service.impl;

import lombok.RequiredArgsConstructor;
import neelgiri.website.constants.RoomStatus;
import neelgiri.website.constants.RoomType;
import neelgiri.website.dto.RoomRequestDto;
import neelgiri.website.dto.RoomResponseDto;
import neelgiri.website.entity.Room;
import neelgiri.website.repository.RoomRepository;
import neelgiri.website.service.RoomService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RoomServiceImpl implements RoomService {

    private final RoomRepository roomRepository;

    // 🔍 SEARCH ROOMS WITH LAZY EXPIRY
    @Override
    public List<RoomResponseDto> searchRoomByFilters(RoomRequestDto roomRequestDto) {

        List<Room> rooms = roomRepository.findAll();

        // 🔥 Lazy expiry: release expired HELD rooms
        for (Room room : rooms) {
            if (room.getRoomStatus() == RoomStatus.HELD &&
                    room.getHoldTime() != null &&
                    room.getHoldTime().isBefore(LocalDateTime.now().minusMinutes(5))) {

                room.setRoomStatus(RoomStatus.UNBOOKED);
                room.setHoldTime(null);
            }
        }

        roomRepository.saveAll(rooms);

        // 🔍 Apply filters
        RoomType type = roomRequestDto.getRoomType();
        RoomStatus status = roomRequestDto.getRoomStatus();

        List<Room> filteredRooms = roomRepository.findRoomByFields(type, status);

        // 🎯 Convert to DTO
        return filteredRooms.stream()
                .map(room -> RoomResponseDto.builder()
                        .roomId(room.getRoomId())
                        .roomNo(room.getRoomNo())
                        .roomType(room.getRoomType())
                        .roomStatus(room.getRoomStatus())
                        .pricePerNight(room.getPricePerNight())
                        .build()
                )
                .toList();
    }

    // 🔒 HOLD ROOM LOGIC
    @Transactional
    @Override
    public void holdRoom(Long roomNo) {

        Room room = roomRepository.findByRoomNo(roomNo);

        // ❌ Safety check
        if (room == null) {
            throw new RuntimeException("Room not found");
        }

        // ❌ Already booked
        if (room.getRoomStatus() == RoomStatus.BOOKED) {
            throw new RuntimeException("Room already booked");
        }

        // 🔥 Handle HELD state
        if (room.getRoomStatus() == RoomStatus.HELD) {

            // If still within hold window → block
            if (room.getHoldTime() != null &&
                    room.getHoldTime().isAfter(LocalDateTime.now().minusMinutes(5))) {

                throw new RuntimeException("Room is currently being booked by someone else");
            }

            // Otherwise → expired → release
            room.setRoomStatus(RoomStatus.UNBOOKED);
            room.setHoldTime(null);
        }

        // ✅ Apply new hold
        room.setRoomStatus(RoomStatus.HELD);
        room.setHoldTime(LocalDateTime.now());

        roomRepository.save(room);
    }


    // RELEASE HOLD LOGIC (called when user navigates away from confirm page)
    @Transactional
    @Override
    public void releaseHold(Long roomNo) {

        Room room = roomRepository.findByRoomNo(roomNo);

        // Silent return - frontend may call this speculatively
        if (room == null) return;

        // Only release if HELD - do not touch BOOKED rooms
        if (room.getRoomStatus() == RoomStatus.HELD) {
            room.setRoomStatus(RoomStatus.UNBOOKED);
            room.setHoldTime(null);
            roomRepository.save(room);
        }
    }
}



//package neelgiri.website.service.impl;
//
//import lombok.RequiredArgsConstructor;
//import neelgiri.website.constants.RoomStatus;
//import neelgiri.website.constants.RoomType;
//import neelgiri.website.dto.RoomRequestDto;
//import neelgiri.website.dto.RoomResponseDto;
//import neelgiri.website.entity.Room;
//import neelgiri.website.repository.RoomRepository;
//import neelgiri.website.service.RoomService;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.time.LocalDateTime;
//import java.util.List;
//
//@Service
//@RequiredArgsConstructor
//public class RoomServiceImpl implements RoomService {
//    private final RoomRepository roomRepository;
//
//    @Override
//    public List<RoomResponseDto> searchRoomByFilters(RoomRequestDto roomRequestDto) {
//        List<Room> rooms = roomRepository.findAll();
//        for (Room room : rooms) {
//            if (room.getRoomStatus() == RoomStatus.HELD &&
//                    room.getHoldTime() != null &&
//                    room.getHoldTime().isBefore(LocalDateTime.now().minusMinutes(5))) {
//
//                room.setRoomStatus(RoomStatus.UNBOOKED);
//                room.setHoldTime(null);
//            }
//        }
//
//        roomRepository.saveAll(rooms);
//
//        RoomType type = roomRequestDto.getRoomType();
//        RoomStatus status = roomRequestDto.getRoomStatus();
//        List<Room> rooms2 = roomRepository.findRoomByFields(type,status);
//
//        return rooms2.stream()
//                .map(room -> RoomResponseDto.builder()
//                        .roomId(room.getRoomId())
//                        .roomNo(room.getRoomNo())
//                        .roomType(room.getRoomType())
//                        .roomStatus(room.getRoomStatus())
//                        .pricePerNight(room.getPricePerNight())
//                        .build()
//                )
//                .toList();
//        //return List.of();
//    }
//
//    @Transactional
//    @Override
//    public void holdRoom(Long roomNo) {
//        Room room = roomRepository.findByRoomNo(roomNo);
//        if (room.getRoomStatus() == RoomStatus.HELD) {
//
//            if (room.getHoldTime() != null &&
//                    room.getHoldTime().isBefore(LocalDateTime.now().minusMinutes(5))) {
//
//                // 🔥 EXPIRED → release it
//                room.setRoomStatus(RoomStatus.UNBOOKED);
//                room.setHoldTime(null);
//
//            } else {
//                throw new RuntimeException("Room is currently being booked by someone else");
//            }
//        }
//
//        if (room == null) {
//            throw new RuntimeException("Room not found");
//        }
//
//        // 🔥 Check if already BOOKED
//        if (room.getRoomStatus() == RoomStatus.BOOKED) {
//            throw new RuntimeException("Room already booked");
//        }
//
//        // 🔥 If HELD, check expiry
//        if (room.getRoomStatus() == RoomStatus.HELD) {
//
//            if (room.getHoldTime() != null &&
//                    room.getHoldTime().isAfter(LocalDateTime.now().minusMinutes(5))) {
//
//                throw new RuntimeException("Room is currently being booked by someone else");
//            }
//        }
//
//        // ✅ Set HOLD
//        room.setRoomStatus(RoomStatus.HELD);
//        room.setHoldTime(LocalDateTime.now());
//
//        roomRepository.save(room);
//    }
//}

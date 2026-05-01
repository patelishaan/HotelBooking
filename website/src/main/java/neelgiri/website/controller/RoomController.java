package neelgiri.website.controller;

import lombok.RequiredArgsConstructor;
import neelgiri.website.constants.RoomStatus;
import neelgiri.website.constants.RoomType;
import neelgiri.website.dto.RoomRequestDto;
import neelgiri.website.dto.RoomResponseDto;
import neelgiri.website.service.RoomService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequiredArgsConstructor
@RequestMapping("/rooms")
public class RoomController {
    private final RoomService roomService;
    @GetMapping("/search")
    public List<RoomResponseDto> searchRooms(
            @RequestParam RoomType roomType
    ) {
        RoomRequestDto request = RoomRequestDto.builder()
                .roomType(roomType)
                .roomStatus(RoomStatus.UNBOOKED)
                .build();

        return roomService.searchRoomByFilters(request);
    }

    @PostMapping("/hold/{roomNo}")
    public String holdRoom(@PathVariable Long roomNo) {
        roomService.holdRoom(roomNo);
        return "Room held";
    }

    @PostMapping("/release/{roomNo}")
    public String releaseHold(@PathVariable Long roomNo) {
        roomService.releaseHold(roomNo);
        return "Room released";
    }
}

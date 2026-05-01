package neelgiri.website.service;

import neelgiri.website.dto.RoomRequestDto;
import neelgiri.website.dto.RoomResponseDto;

import java.util.List;

public interface RoomService {
    List<RoomResponseDto> searchRoomByFilters(RoomRequestDto roomRequestDto);
    void holdRoom(Long roomNo);
    void releaseHold(Long roomNo);
}

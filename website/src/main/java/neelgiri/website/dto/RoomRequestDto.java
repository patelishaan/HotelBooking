package neelgiri.website.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import neelgiri.website.constants.RoomStatus;
import neelgiri.website.constants.RoomType;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RoomRequestDto {
    private RoomType roomType;
    private RoomStatus roomStatus;

}

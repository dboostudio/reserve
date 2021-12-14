package studio.dboo.reserve.api.room.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import studio.dboo.reserve.api.room.entity.Room;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RoomForm {

    private Long innId;
    private Room room;
}
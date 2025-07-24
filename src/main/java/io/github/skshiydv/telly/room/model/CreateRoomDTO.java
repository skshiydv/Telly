package io.github.skshiydv.telly.room.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateRoomDTO {
    private String roomName;
    private String roomType         ;
    private String roomDescription;
}

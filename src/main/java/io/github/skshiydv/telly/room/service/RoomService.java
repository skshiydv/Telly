package io.github.skshiydv.telly.room.service;

import io.github.skshiydv.telly.room.model.CreateRoomDTO;
import io.github.skshiydv.telly.room.model.GetRoomDto;

public interface RoomService {
     String createRoom(CreateRoomDTO createRoomDTO);

    GetRoomDto getRoomByRoomName(String roomName);

    String addUser(String roomName);
}

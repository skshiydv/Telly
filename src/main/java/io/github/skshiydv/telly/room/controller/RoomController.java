package io.github.skshiydv.telly.room.controller;

import io.github.skshiydv.telly.core.response.ApiResponse;
import io.github.skshiydv.telly.room.model.CreateRoomDTO;
import io.github.skshiydv.telly.room.model.GetRoomDto;
import io.github.skshiydv.telly.room.service.RoomService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class RoomController {
    private final RoomService roomService;

    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    @PostMapping
    public ResponseEntity<ApiResponse> createRoom(@RequestBody CreateRoomDTO createRoomDTO) {
        String response = roomService.createRoom(createRoomDTO);
        if (response.equals("ok")) {
            return ResponseEntity.ok(new ApiResponse(true, "Room created successfully."));

        }
        return ResponseEntity.badRequest().body(new ApiResponse(false, "Fill all fields correctly."));
    }

    @GetMapping("/{roomName}")
    public ResponseEntity<GetRoomDto> getRoom(@PathVariable String roomName) {
        GetRoomDto res = roomService.getRoomByRoomName(roomName);
        if (res == null) {
            return ResponseEntity.notFound().build();
        }
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @PostMapping("/{roomName}")
    public ResponseEntity<String> addUser(@RequestParam String roomName){
        roomService.addUser(roomName);
        return new ResponseEntity<>("ok", HttpStatus.OK);
    }
}


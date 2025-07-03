package io.github.skshiydv.telly.room.serviceImpl;

import io.github.skshiydv.telly.room.entity.RoomEntity;
import io.github.skshiydv.telly.room.mapper.CreateRoomDTOtoRoomEntity;
import io.github.skshiydv.telly.room.mapper.RoomEntityToGetRoomDtoMapper;
import io.github.skshiydv.telly.room.model.CreateRoomDTO;
import io.github.skshiydv.telly.room.model.GetRoomDto;
import io.github.skshiydv.telly.room.repository.RoomRepository;
import io.github.skshiydv.telly.room.service.RoomService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class RoomServiceImpl implements RoomService {
    private final RoomRepository roomRepository;

    public RoomServiceImpl(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    @Override
    public String createRoom(CreateRoomDTO createRoomDTO) {
        if (roomRepository.findByRoomName(createRoomDTO.getRoomName())!=null) return "Room already exists";
        if (createRoomDTO.getRoomName() == null || createRoomDTO.getRoomName().isEmpty()) {
            return "Room name is empty";
        }
        if (createRoomDTO.getRoomId() == null || createRoomDTO.getRoomId().isEmpty()) {
            return "Room id is empty";
        }
        if (createRoomDTO.getRoomDescription() == null || createRoomDTO.getRoomDescription().isEmpty()) {
            return "Room description is empty";
        }
        RoomEntity entity = new RoomEntity();
        entity = CreateRoomDTOtoRoomEntity.INSTANCE.apply(createRoomDTO);
        roomRepository.save(entity);
        return "ok";
    }

    @Override
    public GetRoomDto getRoomByRoomName(String roomName) {
        if(roomName==null || roomName.isEmpty()) return null;
        if(roomRepository.findByRoomName(roomName)==null) return null;
        GetRoomDto dto = RoomEntityToGetRoomDtoMapper.INSTANCE.apply(roomRepository.findByRoomName(roomName));
        return dto ;
    }

    @Override
    public String addUser(String roomName) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return "";
    }
}

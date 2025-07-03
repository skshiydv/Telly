package io.github.skshiydv.telly.room.repository;

import io.github.skshiydv.telly.room.entity.RoomEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomRepository extends JpaRepository<RoomEntity,Long> {
    RoomEntity findByRoomName(String roomName);
}

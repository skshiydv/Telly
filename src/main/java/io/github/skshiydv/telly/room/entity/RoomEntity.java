package io.github.skshiydv.telly.room.entity;

import io.github.skshiydv.telly.message.entity.MessageEntity;
import io.github.skshiydv.telly.user.entity.UserEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "room")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RoomEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "room_name")
    @NonNull
    private String roomName;
    @Column(name = "room_id")
    @NonNull
   private String roomId;
    @Column(name = "room_description")
    @NonNull
    private String roomDescription;
    @OneToMany
    private List<MessageEntity> messages = new ArrayList<>();
    @OneToMany
    private List<UserEntity> users = new ArrayList<>();

}

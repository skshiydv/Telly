package io.github.skshiydv.telly.user.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "user_entity")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;           // From Google "name"
    private String email;              // From Google "email"
//
//    private String provider;           // e.g. "google"
//    private String providerId;         // e.g. Google sub/id
    private String imageUrl;           // profile picture

//    @Column(name = "password")
//    private String password;           // Optional (null for Google users)
}

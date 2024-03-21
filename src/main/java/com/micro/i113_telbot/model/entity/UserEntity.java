package com.micro.i113_telbot.model.entity;

import com.micro.i113_telbot.model.UserStatus;
import javax.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "users")
public class UserEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String userId;
    private String userSecretCode;

    private String chatId;
    private String userName;
    private String firstName;
    private String lastName;

    @Enumerated(EnumType.STRING)
    private UserStatus status;
}

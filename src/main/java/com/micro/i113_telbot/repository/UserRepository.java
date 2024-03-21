package com.micro.i113_telbot.repository;

import com.micro.i113_telbot.model.entity.UserEntity;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {

    Optional<UserEntity> findUserEntityByUserSecretCode(@NonNull String userSecretCode);
    Optional<UserEntity> findUserEntityByUserId(@NonNull String userId);
    Optional<UserEntity> findUserEntityByChatId(String chatId);
}

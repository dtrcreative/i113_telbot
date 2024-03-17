package com.micro.i113_telbot.service.converter;

import com.micro.i113_telbot.model.dto.TelegramRegisterDto;
import com.micro.i113_telbot.model.entity.UserEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class RegistrationConverter {

    public UserEntity convertToEntity(TelegramRegisterDto inputDTO){
        return UserEntity.builder()
                .userId(inputDTO.getUserId())
                .userSecretCode(inputDTO.getUserSecretKey())
                .build();
    }

    public TelegramRegisterDto convertToDto(UserEntity inputEntity) {
        return TelegramRegisterDto.builder()
                .userId(inputEntity.getUserId())
                .userSecretKey(inputEntity.getUserSecretCode())
                .build();
    }

}

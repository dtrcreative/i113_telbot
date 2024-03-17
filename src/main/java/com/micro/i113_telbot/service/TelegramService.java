package com.micro.i113_telbot.service;

import com.micro.i113_telbot.model.dto.TelegramRegisterDto;
import com.micro.i113_telbot.model.entity.UserEntity;
import com.micro.i113_telbot.repository.UserRepository;
import com.micro.i113_telbot.service.converter.RegistrationConverter;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class TelegramService {

    private UserRepository repository;
    private RegistrationConverter converter;

    public TelegramRegisterDto register(TelegramRegisterDto registerDto){
        registerDto.setUserSecretKey(generateRandomSecretKey());
        UserEntity entity = repository.save(converter.convertToEntity(registerDto));
        return converter.convertToDto(entity);
    }

    public TelegramRegisterDto checkUserStatus(TelegramRegisterDto registerDto){
        Optional<UserEntity> entity = repository.findUserEntityByUserId(registerDto.getUserId());
        if(entity.isPresent()){
            return converter.convertToDto(entity.get());
        }
        return registerDto;
    }

    private String generateRandomSecretKey(){
        int min = 100000;
        int max = 999999;
        max -= min;
        int result =  (int) (Math.random() * ++max) + min;
        return String.valueOf(result);
    }


}

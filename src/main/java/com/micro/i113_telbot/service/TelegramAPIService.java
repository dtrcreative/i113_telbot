package com.micro.i113_telbot.service;

import com.micro.i113_telbot.controller.telegrm.TelegramBot;
import com.micro.i113_telbot.model.UserStatus;
import com.micro.i113_telbot.model.dto.TelegramMessageDto;
import com.micro.i113_telbot.model.dto.TelegramRegisterDto;
import com.micro.i113_telbot.model.entity.UserEntity;
import com.micro.i113_telbot.model.telegram.TelegramResponse;
import com.micro.i113_telbot.repository.UserRepository;
import com.micro.i113_telbot.service.converter.RegistrationConverter;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class TelegramAPIService {

    private UserRepository repository;
    private RegistrationConverter converter;
    private TelegramBot bot;

    public TelegramRegisterDto register(TelegramRegisterDto registerDto){
        Optional<UserEntity> userEntity = repository.findUserEntityByUserId(registerDto.getUserId());
        if(userEntity.isEmpty()){
            registerDto.setUserSecretKey(generateRandomSecretKey());
            registerDto.setUserStatus(UserStatus.NOTCONFIRMED);
            UserEntity entity = repository.save(converter.convertToEntity(registerDto));
            return converter.convertToDto(entity);
        }
        return converter.convertToDto(userEntity.get());
    }

    public TelegramRegisterDto checkUserStatus(TelegramRegisterDto registerDto){
        Optional<UserEntity> entity = repository.findUserEntityByUserId(registerDto.getUserId());
        if(entity.isPresent()){
            return converter.convertToDto(entity.get());
        }
        return registerDto;
    }

    public TelegramRegisterDto disableUser(TelegramRegisterDto registerDto){
        Optional<UserEntity> entity = repository.findUserEntityByUserId(registerDto.getUserId());
        if(entity.isPresent()){
            entity.get().setStatus(UserStatus.DISABLED);
            return converter.convertToDto(repository.save(entity.get()));
        }
        return registerDto;
    }

    public TelegramRegisterDto enableUser(TelegramRegisterDto registerDto){
        Optional<UserEntity> entity = repository.findUserEntityByUserId(registerDto.getUserId());
        if(entity.isPresent()){
            if(entity.get().getChatId()==null){
                entity.get().setStatus(UserStatus.NOTCONFIRMED);
            }else {
                entity.get().setStatus(UserStatus.ACTIVE);
            }
            return converter.convertToDto(repository.save(entity.get()));
        }
        return registerDto;
    }

    public void handleMultipleDto(List<TelegramMessageDto> dtos){
        for(TelegramMessageDto dto: dtos){
            Optional<UserEntity> user = repository.findUserEntityByUserId(dto.getUserId());
            if(user.isPresent() && user.get().getStatus().equals(UserStatus.ACTIVE)){
                TelegramResponse response = TelegramResponse.builder()
                        .user(user.get())
                        .message(dto.getMessage())
                        .build();
                try{
                    bot.sendMessage(response);
                }catch (TelegramApiException e) {
                    throw new RuntimeException(e);
                }
            }
        }

    }

    private String generateRandomSecretKey(){
        int min = 100000;
        int max = 999999;
        max -= min;
        int result =  (int) (Math.random() * ++max) + min;
        return String.valueOf(result);
    }


}

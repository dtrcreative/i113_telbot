package com.micro.i113_telbot.service.converter;

import com.micro.i113_telbot.model.entity.UserEntity;
import com.micro.i113_telbot.model.telegram.TelegramRequest;
import com.micro.i113_telbot.model.telegram.TelegramResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class TelegramConverter {

    public TelegramResponse prepareResponse(UserEntity entity, String message){
        return TelegramResponse.builder()
                .user(entity)
                .message(message)
                .build();
    }

    public TelegramResponse prepareResponse(TelegramRequest request, String message){
        UserEntity entity = UserEntity.builder()
                .chatId(request.getChatId())
                .userName(request.getUserName())
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .build();
        return TelegramResponse.builder()
                .user(entity)
                .message(message)
                .build();
    }
}

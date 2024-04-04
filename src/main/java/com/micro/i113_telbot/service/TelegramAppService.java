package com.micro.i113_telbot.service;

import com.micro.i113_telbot.model.UserStatus;
import com.micro.i113_telbot.model.entity.UserEntity;
import com.micro.i113_telbot.model.telegram.TelegramRequest;
import com.micro.i113_telbot.model.telegram.TelegramResponse;
import com.micro.i113_telbot.repository.UserRepository;
import com.micro.i113_telbot.service.converter.RegistrationConverter;
import com.micro.i113_telbot.service.converter.TelegramConverter;
import lombok.AllArgsConstructor;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Optional;

@AllArgsConstructor
@Service
public class TelegramAppService {

    private UserRepository repository;
    private RegistrationConverter registrationConverter;
    private TelegramConverter telegramConverter;
    private MessageService messages;

    public TelegramResponse handleRequest(TelegramRequest request){
        defineStatus(request);
        switch (request.getStatus()){
            case ACTIVE -> {
                return telegramConverter.prepareResponse(request, messages.getMessage(messages.NOCOMMANDSYET));
            }
            case NOTCONFIRMED -> {
                confirmUser(request);
                return telegramConverter.prepareResponse(request, messages.getMessage(messages.CONFIRMED));
            }
            case NOTREGISTERED -> {
                return telegramConverter.prepareResponse(request, messages.getMessage(messages.NOTREGISTERED));
            }
            case UNDEFINED -> {
                return telegramConverter.prepareResponse(request, messages.getMessage(messages.UNDEFINED));
            }
            case REMOVED -> {
                return telegramConverter.prepareResponse(request, messages.getMessage(messages.REMOVED));
            }
            default -> {
                return telegramConverter.prepareResponse(request, messages.getMessage(messages.ERROR));
            }
        }
    }

    private void confirmUser(TelegramRequest request){
        Optional<UserEntity> entity = repository.findUserEntityByUserSecretCode(request.getMessage());
        if(entity.isPresent()){
            registrationConverter.activateUser(entity.get(), request);
            repository.save(entity.get());
        }
    }

    private void defineStatus(TelegramRequest request){
        Optional<UserEntity> entity = repository.findUserEntityByChatId(request.getChatId());
        if(entity.isPresent()){
            request.setStatus(entity.get().getStatus());
        }else{
            int secretCodeLength = 6;
            if(request.getMessage().length() == secretCodeLength && StringUtils.isNumeric(request.getMessage())){
                entity = repository.findUserEntityByUserSecretCode(request.getMessage());
                if(entity.isPresent()){
                    request.setStatus(UserStatus.NOTCONFIRMED);
                }else{
                    request.setStatus(UserStatus.NOTREGISTERED);
                }
            }else{
                request.setStatus(UserStatus.UNDEFINED);
            }
        }
    }
}

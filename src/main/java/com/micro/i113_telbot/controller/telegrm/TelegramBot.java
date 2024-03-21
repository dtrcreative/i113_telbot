package com.micro.i113_telbot.controller.telegrm;

import com.micro.i113_telbot.model.UserStatus;
import com.micro.i113_telbot.model.telegram.TelegramRequest;
import com.micro.i113_telbot.model.telegram.TelegramResponse;
import com.micro.i113_telbot.service.TelegramAppService;
import com.micro.i113_telbot.service.converter.TelegramConverter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Component
public class TelegramBot extends TelegramLongPollingBot {

    @Value("${telegram.botname}")
    private String BOT_NAME;
    @Value("${telegram.bottoken}")
    private String BOT_TOKEN;
    @Value("${telegram.botrootchatid}")
    private String BOT_ROOT;

    private final TelegramAppService service;

    public TelegramBot(TelegramAppService service){
        this.service = service;
    }

    @Override
    public String getBotUsername() {
        return BOT_NAME;
    }

    @Override
    public String getBotToken() {
        return BOT_TOKEN;
    }

    @Override
    public void onRegister() {
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            try {
                sendMessage(service.handleRequest(fillRequest(update.getMessage())));
            } catch (TelegramApiException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void sendMessage(TelegramResponse response) throws TelegramApiException {
        SendMessage outMess = new SendMessage();
//        setKeys(outMess); //TODO Configure how understand wich user uses some Api
        outMess.setChatId(response.getUser().getChatId());
        outMess.setText(response.getMessage());
        execute(outMess);
    }

    public TelegramRequest fillRequest(Message message) {
        return TelegramRequest.builder()
                .chatId(String.valueOf(message.getChatId()))
                .messageId(String.valueOf(message.getMessageId()))
                .message(message.getText())
                .firstName(message.getChat().getFirstName())
                .lastName(message.getChat().getFirstName())
                .userName(message.getChat().getUserName())
                .status(UserStatus.UNDEFINED)
                .build();
    }
}

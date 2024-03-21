package com.micro.i113_telbot.appconfig;

import com.micro.i113_telbot.controller.telegrm.TelegramBot;
import com.micro.i113_telbot.service.MessageService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import javax.annotation.PostConstruct;

@Slf4j
@Configuration
@AllArgsConstructor
public class ApplicationConfiguration {

    TelegramBot bot;

//    @Bean
//    public MessageService getMessageService(MessageSource messageSource) {
//        return new MessageService(messageSource);
//    }

    @PostConstruct
    public void registerTelegramBot() {
        try {
            TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
            telegramBotsApi.registerBot(bot);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

}

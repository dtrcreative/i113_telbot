package com.micro.i113_telbot.service;

import lombok.AllArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Component
@AllArgsConstructor
public class MessageService {

    private final MessageSource messageSource;

    private final String regexAdd = "(/+.{3,}\\W{1}.{3,100}\\W{1}.{3,100}\\W{1}\\d{4}[-]\\d{2}[-]\\d{2})";
    private final String regexUpdate = "(/+.{3,}\\W{1}\\d{1,3}\\W{1}.{3,100}\\W{1}.{3,100}\\W{1}\\d{4}[-]\\d{2}[-]\\d{2})";
    private final String regexDelete = "(/+.{3,}\\W{1}\\d{1,3})";

    public final String HELLO = "telegram.registrtion.hello";
    public final String NOTREGISTERED = "telegram.registrtion.notregistered";
    public final String CONFIRMED = "telegram.registrtion.confirmed";
    public final String UNDEFINED = "telegram.registrtion.undefined";
    public final String REMOVED = "telegram.registrtion.removed";
    public final String DISABLED = "telegram.answer.disabled";
    public final String ERROR = "telegram.registrtion.error";
    public final String NOCOMMANDSYET = "telegram.answer.nocommands";

    public String getMessage(String code) {
        return messageSource.getMessage(code, null, Locale.getDefault());
    }

}

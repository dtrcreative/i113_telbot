package com.micro.i113_telbot.model.telegram;

import com.micro.i113_telbot.model.UserStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TelegramRequest {

    private String chatId;
    private String messageId;
    private String message;
    private String firstName;
    private String lastName;
    private String userName;
    private UserStatus status;

}

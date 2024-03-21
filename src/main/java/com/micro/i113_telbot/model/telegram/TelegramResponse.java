package com.micro.i113_telbot.model.telegram;

import com.micro.i113_telbot.model.entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TelegramResponse {

    private UserEntity user;
    private String message;

}

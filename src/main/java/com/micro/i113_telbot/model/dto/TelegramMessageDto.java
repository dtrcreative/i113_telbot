package com.micro.i113_telbot.model.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TelegramMessageDto {

    @NonNull
    private String userId;
    @NonNull
    private String message;
}

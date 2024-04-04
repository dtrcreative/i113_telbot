package com.micro.i113_telbot.model.dto;

import com.micro.i113_telbot.model.UserStatus;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TelegramRegisterDto {

    @NonNull
    private String userId;
    private String userSecretKey;
    private UserStatus userStatus = UserStatus.UNDEFINED;
}

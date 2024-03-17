package com.micro.i113_telbot.controller;

import com.micro.i113_telbot.model.dto.TelegramRegisterDto;
import com.micro.i113_telbot.service.TelegramService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/telbot/")
public class TelegramController {

    private final TelegramService service;

    public TelegramController(TelegramService service) {
        this.service = service;
    }

    @PostMapping("/status")
    public TelegramRegisterDto getStatus(@RequestBody TelegramRegisterDto unitDto){
        return service.checkUserStatus(unitDto);
    }

    @PostMapping("/reg")
    public TelegramRegisterDto registerUser(@RequestBody TelegramRegisterDto unitDto) {
        return service.register(unitDto);
    }

}

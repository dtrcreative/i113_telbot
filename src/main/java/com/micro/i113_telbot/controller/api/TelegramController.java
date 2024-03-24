package com.micro.i113_telbot.controller.api;

import com.micro.i113_telbot.model.dto.TelegramMessageDto;
import com.micro.i113_telbot.model.dto.TelegramRegisterDto;
import com.micro.i113_telbot.service.TelegramAPIService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/telbot/")
public class TelegramController {

    private final TelegramAPIService service;

    public TelegramController(TelegramAPIService service) {
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

    @PostMapping("/")
    public void multipleDto(@RequestBody List<TelegramMessageDto> unitsDtoList) {
        service.handleMultipleDto(unitsDtoList);
    }

}

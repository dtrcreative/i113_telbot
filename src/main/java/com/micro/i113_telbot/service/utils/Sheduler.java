package com.micro.i113_telbot.service.utils;

import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class Sheduler {

    @Scheduled(cron = "${sheduling.interval-in-cron-evening}")
    public void eveningSheduler() {
//        action();
    }
    @Scheduled(cron = "${sheduling.interval-in-cron-morning}")
    public void morningSheduler() {
//        action();
    }


}

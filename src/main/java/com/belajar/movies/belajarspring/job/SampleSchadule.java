package com.belajar.movies.belajarspring.job;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

@Slf4j
@Component
public class SampleSchadule {

    @Scheduled(cron = "0 0/1 * 1/1 * *") //run tiap 2 menit
    public void execute(){
        log.info("Schedule Task : current time: "+ new Date());
    }

}

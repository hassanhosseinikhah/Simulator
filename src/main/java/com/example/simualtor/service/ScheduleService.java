package com.example.simualtor.service;

import com.example.simualtor.file.ReadFromFile;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@Slf4j
@RequiredArgsConstructor
public class ScheduleService {

//    @Value("${cronJobSchFile}")
//    private String cronJobSchFile;
//
//    @Value("${cronJobSchSnmp}")
//    public String cronJobSchSnmp;



    private final ReadFromFile readFromFile;
    private final TrapSender trapSender;
    @Scheduled(fixedRate =  60 * 60 * 1000)
    public void cronJobSchFile() throws IOException {

        log.info("****************************************************");
        log.info("****START cronJobSch");
        readFromFile.read();
        log.info("****END cronJobSch");
        log.info("**********************************************************");

    }

//    @Scheduled(fixedRate = 60 * 60 * 1000)
//    public void cronJobSchSnmp() throws IOException {
//
//        log.info("****************************************************");
//        log.info("****START cronJobSch");
//        trapSender.sendTrap();
//        log.info("****END cronJobSch");
//        log.info("**********************************************************");
//
//    }


}

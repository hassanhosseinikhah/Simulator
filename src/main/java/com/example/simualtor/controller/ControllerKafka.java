package com.example.simualtor.controller;

import com.example.simualtor.DTO.Alarm;
import com.example.simualtor.exception.RException;
import com.example.simualtor.service.SendToKafkaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping(value = "/simulator")
@Slf4j
public class ControllerKafka {

    private final SendToKafkaService sendToKafkaService;
    @Value("${RMS.kafka.topic}")
    private String topicName;


    @Autowired
    public ControllerKafka(SendToKafkaService sendToKafkaService) {
        this.sendToKafkaService = sendToKafkaService;

    }

    @PostMapping(path = "/createAlarmDto")
    public ResponseEntity<?> createAlarmDto(@RequestBody String alarmDto) {
        log.info("start create alarmDto");
        try {
            sendToKafkaService.sender(alarmDto);
            return new ResponseEntity<>("alarmDto send", HttpStatus.OK);
        } catch (RException | IOException rException) {
            return new ResponseEntity<>(rException.getMessage(), HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    @Async
    public CompletableFuture<ResponseEntity<?>> create(@RequestBody String alarmDto) throws InterruptedException {
        String[] alarms = alarmDto.split("}");
        CompletableFuture<ResponseEntity<?>> r = null;
        for (String alarm : alarms) {
            try {
                sendToKafkaService.sender(alarm);
                r = CompletableFuture.completedFuture(new ResponseEntity<>("alarmDto send", HttpStatus.OK));
                Thread.sleep(5000);
                return r;
            } catch (RException | IOException rException) {
                RException exception = new RException("alarm not send", 500);
                return CompletableFuture.completedFuture(new ResponseEntity<>(exception, HttpStatus.NOT_ACCEPTABLE));
            }
        }
        return r;
    }

    @RequestMapping(value = "/alarm", method = RequestMethod.POST)
    @Async
    public CompletableFuture<ResponseEntity<?>> generateDownAlarmScenario(@RequestBody List<Alarm> alarms) throws InterruptedException {
        log.info("start create alarmDto");

        sendToKafkaService.sendAlarm(alarms);
        return CompletableFuture.completedFuture(new ResponseEntity<>("alarmDto send", HttpStatus.OK));

    }
}
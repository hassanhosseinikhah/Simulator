package com.example.simualtor.service;

import com.example.simualtor.enums.AlarmFieldEnum;
import com.example.simualtor.thread.AlarmDTOEventPublisher;
import com.example.simualtor.thread.SendToKafkaRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Map;

@Service
@Slf4j
@RequiredArgsConstructor
public class SendToKafkaRequestScheduling {
    private final ApplicationEventPublisher publisher;
    private final ThreadPoolTaskScheduler taskScheduler;


    public void sendToKafka(Map<AlarmFieldEnum, String> alarmEnumMap, Map<String, String> alarmOIDMapValues) throws InterruptedException {

        SendToKafkaRequest sendToKafkaRequest = new SendToKafkaRequest(alarmEnumMap);
        AlarmDTOEventPublisher alarmDTOEventPublisher = new AlarmDTOEventPublisher(publisher, sendToKafkaRequest);
        taskScheduler.schedule(alarmDTOEventPublisher, new Date(System.currentTimeMillis()));
        log.info("Publishing task: {}\n{}", sendToKafkaRequest, new Date(System.currentTimeMillis() + kafkaSendOnTime(alarmOIDMapValues)));
    }

    private int kafkaSendOnTime(Map<String, String> alarmOIDMapValues) {
        LocalTime timestamp = LocalTime.parse(alarmOIDMapValues.get("2011.2.15.2.4.3.3.3.0").substring(11), DateTimeFormatter.ofPattern("HH:mm:ss"));
        LocalTime now = LocalTime.now();
        System.out.println(timestamp);
        System.out.println(now);

        return (1000 * (((timestamp.getHour() - now.getHour()) * 60 + (timestamp.getMinute() - now.getMinute())) * 60 + (timestamp.getSecond() - now.getSecond())));


    }

}

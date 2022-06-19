package com.example.simualtor.thread;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;


@Slf4j
public class AlarmDTOEventPublisher implements Runnable{
    private final ApplicationEventPublisher applicationEventPublisher;
    private final SendToKafkaRequest sendToKafkaRequest;

    public AlarmDTOEventPublisher(ApplicationEventPublisher applicationEventPublisher, SendToKafkaRequest sendToKafkaRequest) {
        this.applicationEventPublisher = applicationEventPublisher;
        this.sendToKafkaRequest = sendToKafkaRequest;
    }

    @Override
    public void run() {
        AlarmMapEvent alarmMapEvent =new AlarmMapEvent(this,sendToKafkaRequest);
        applicationEventPublisher.publishEvent(alarmMapEvent);
    }
}

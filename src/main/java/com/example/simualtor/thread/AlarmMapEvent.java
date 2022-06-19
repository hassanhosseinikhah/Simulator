package com.example.simualtor.thread;

import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;


@Getter
@Setter
public class AlarmMapEvent extends ApplicationEvent {

    private final SendToKafkaRequest sendToKafkaRequest;

    AlarmMapEvent(Object source, SendToKafkaRequest sendToKafkaRequest) {
        super(source);
        this.sendToKafkaRequest=sendToKafkaRequest;
    }

}

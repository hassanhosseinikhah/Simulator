package com.example.simualtor.thread;

import com.example.simualtor.enums.AlarmFieldEnum;
import lombok.Getter;

import java.io.Serializable;
import java.util.Map;

@Getter
public class SendToKafkaRequest implements Serializable {
    private final Map<AlarmFieldEnum,String> alarmEnumMap;

    public SendToKafkaRequest(Map<AlarmFieldEnum,String> alarmEnumMap) {
        this.alarmEnumMap=alarmEnumMap;
    }

}

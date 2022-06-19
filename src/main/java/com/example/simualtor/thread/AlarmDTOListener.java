package com.example.simualtor.thread;

import com.example.simualtor.enums.AlarmFieldEnum;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@Slf4j
public class AlarmDTOListener implements ApplicationListener<AlarmMapEvent> {
    private KafkaTemplate kafkaTemplate;
    private Gson gson;

    public AlarmDTOListener(KafkaTemplate kafkaTemplate, Gson gson) {
        this.kafkaTemplate = kafkaTemplate;
        this.gson = gson;
    }

    @Override
    public void onApplicationEvent(AlarmMapEvent event) {
        log.info("Start onApp");
        try {
            String clarityTime = new Date().toString();
//            event.getSendToKafkaRequest().getAlarmEnumMap().put(AlarmFieldEnum.getEnum("clarityTime"), clarityTime);
            kafkaTemplate.send("AlarmFieldEnum7", gson.toJson(event.getSendToKafkaRequest().getAlarmEnumMap()));
            log.info("successfully send to kafka");
        } catch (Exception e) {
            System.err.println();
            e.printStackTrace();
            log.info("end  onApp ,AlarmDto:  " + event.getSendToKafkaRequest().getAlarmEnumMap());
        }
    }
}

package com.example.simualtor.service;

import com.example.simualtor.DTO.Alarm;
import com.example.simualtor.exception.RException;
import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.List;


@Service
@Slf4j
@RequiredArgsConstructor
public class SendToKafkaService {

    private final KafkaTemplate kafkaTemplate;
    private final Gson gson;
    @Value("${RMS.kafka.topic}")
    private String topicName;

    public void sender(String alarmDto) throws RException, IOException {
        log.info("start sent to kafka");
        kafkaTemplate.send("RMS1", gson.toJson(alarmDto));
    }

    public void sendAlarm(List<Alarm> alarms) throws InterruptedException {
        log.info("start sent to kafka");
        for (Alarm alarm : alarms) {
            kafkaTemplate.send(topicName, gson.toJson(alarm));

        }
        log.info("alarm sends");
    }

    public static byte[] serializePDUToString(Alarm o) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(o);
        oos.close();
        return baos.toByteArray();
    }
}
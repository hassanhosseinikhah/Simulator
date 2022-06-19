package com.example.simualtor.service;

import com.example.simualtor.thread.PDUEventPublisher;
import com.example.simualtor.thread.SendPduRequest;
import org.snmp4j.PDU;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@Service
public class SendPDURequestScheduling {
    private final ApplicationEventPublisher publisher;
    private final ThreadPoolTaskScheduler taskScheduler;

    public SendPDURequestScheduling(ApplicationEventPublisher publisher, ThreadPoolTaskScheduler taskScheduler) {
        this.publisher = publisher;
        this.taskScheduler = taskScheduler;
    }
    public void sendPDU(PDU pdu){
        SendPduRequest sendPduRequest=new SendPduRequest(pdu);
        PDUEventPublisher pduEventPublisher= new PDUEventPublisher(publisher, sendPduRequest);
        taskScheduler.schedule(pduEventPublisher,new Date(System.currentTimeMillis() ));


    }

    public int onTimeSend(PDU pdu){
        String string=pdu.get(2).toString().substring(22);
        String[] s=pdu.get(2).toString().split("=");
        string.length();
        LocalTime timestamp = LocalTime.parse(s[1], DateTimeFormatter.ofPattern("HH:mm:ss"));
        LocalTime now = LocalTime.now();
        System.out.println(timestamp);
        System.out.println(now);
//        if (timestamp.isAfter(now)) {
//            Thread.sleep(1000 * (((timestamp.getHour() - now.getHour()) * 60 + (timestamp.getMinute() - now.getMinute())) * 60 + (timestamp.getSecond() - now.getSecond())));
//        }
        return (1000 * (((timestamp.getHour() - now.getHour()) * 60 + (timestamp.getMinute() - now.getMinute())) * 60 + (timestamp.getSecond() - now.getSecond())));

    }
}

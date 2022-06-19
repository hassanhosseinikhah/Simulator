package com.example.simualtor.thread;


import org.springframework.context.ApplicationEventPublisher;

public class PDUEventPublisher implements Runnable {

    private final ApplicationEventPublisher applicationEventPublisher;
    private final SendPduRequest sendPduRequest;

    public PDUEventPublisher(ApplicationEventPublisher applicationEventPublisher, SendPduRequest sendPduRequest) {
        this.applicationEventPublisher = applicationEventPublisher;
        this.sendPduRequest = sendPduRequest;
    }

    @Override
    public void run() {
        PDUEvent pduEvent=new PDUEvent(this,sendPduRequest);
        applicationEventPublisher.publishEvent(pduEvent);

    }
}

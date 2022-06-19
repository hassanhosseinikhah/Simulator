package com.example.simualtor.thread;


import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;

@Getter
@Setter
public class PDUEvent extends ApplicationEvent {

    private SendPduRequest sendPduRequest;

     PDUEvent(Object source, SendPduRequest sendPduRequest) {
        super(source);
        this.sendPduRequest=sendPduRequest;
    }


}

package com.example.simualtor.thread;

import lombok.Getter;
import lombok.Setter;
import org.snmp4j.PDU;

import java.io.Serializable;

@Getter
@Setter
public class SendPduRequest implements Serializable {

    private PDU pdu;

    public SendPduRequest(PDU pdu) {
        this.pdu = pdu;
    }

    @Override
    public String toString() {
        return "SendPduRequest{" +
                "pdu=" + pdu +
                '}';
    }
}

package com.example.simualtor.thread;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.snmp4j.CommunityTarget;
import org.snmp4j.Snmp;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
@RequiredArgsConstructor
@Slf4j
public class PDUListener  implements ApplicationListener<PDUEvent> {

    private  final Snmp snmp;
    private final CommunityTarget cTarget;
    public static final String community = "public";
    public static final String ipAddress = "127.0.0.1";
    public static final int port = 162;
    public static int counter = 1;

    @SneakyThrows
    @Override
    public void onApplicationEvent(PDUEvent event) {
        log.info("start send pdu");
        snmp.send(event.getSendPduRequest().getPdu(),cTarget);
        log.info("end send pdu");
    }
}

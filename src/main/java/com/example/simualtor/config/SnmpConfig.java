package com.example.simualtor.config;

import org.snmp4j.CommunityTarget;
import org.snmp4j.Snmp;
import org.snmp4j.TransportMapping;
import org.snmp4j.mp.SnmpConstants;
import org.snmp4j.smi.OctetString;
import org.snmp4j.smi.UdpAddress;
import org.snmp4j.transport.DefaultUdpTransportMapping;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

@Configuration
public class SnmpConfig {
    public static final String community = "public";
    public static final String ipAddress = "192.168.19.104";
    public static final int port =1163;




    @Bean
    public TransportMapping getTransportMapping() throws IOException {
        TransportMapping transportMapping=new DefaultUdpTransportMapping();
        transportMapping.listen();
        return transportMapping;
    }

    @Bean
    public Snmp getSnmp(TransportMapping transportMapping){
        return new Snmp(transportMapping);
    }
    @Bean
    public CommunityTarget getCommunityTarget(){
        CommunityTarget cTarget = new CommunityTarget();
        cTarget.setCommunity(new OctetString(community));
        cTarget.setVersion(SnmpConstants.version2c);
        cTarget.setAddress(new UdpAddress(ipAddress + "/" + port));
        cTarget.setRetries(0);
        cTarget.setTimeout(10);
        return cTarget;
    }
}

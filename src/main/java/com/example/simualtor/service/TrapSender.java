package com.example.simualtor.service;

import org.snmp4j.CommunityTarget;
import org.snmp4j.PDU;
import org.snmp4j.Snmp;
import org.snmp4j.TransportMapping;
import org.snmp4j.mp.SnmpConstants;
import org.snmp4j.smi.OID;
import org.snmp4j.smi.OctetString;
import org.snmp4j.smi.UdpAddress;
import org.snmp4j.smi.VariableBinding;
import org.snmp4j.transport.DefaultUdpTransportMapping;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class TrapSender {
    private final SendPDURequestScheduling sendPDURequestScheduling;

    public static final String community = "public";
    public static final String ipAddress = "127.0.0.1";
    public static final int port = 162;
    public static int counter = 1;

    public TrapSender(SendPDURequestScheduling sendPDURequestScheduling) {
        this.sendPDURequestScheduling = sendPDURequestScheduling;
    }


    public void sendTrap() {
        try {

            CommunityTarget cTarget = new CommunityTarget();
            cTarget.setCommunity(new OctetString(community));
            cTarget.setVersion(SnmpConstants.version2c);
            cTarget.setAddress(new UdpAddress(ipAddress + "/" + port));
            cTarget.setRetries(0);
            cTarget.setTimeout(10);

            Scanner scanner = new Scanner(new File("F:\\u2k2.txt"));
            Date date = new Date();
            System.out.println(date);


            while (scanner.hasNext()) {
                String line = "";
                do {
                    if (!scanner.hasNextLine())
                        break;
                    line = scanner.nextLine();
                    line += "\n";
                }
                while (!line.contains("}"));
                Matcher matcher = Pattern.compile("E:[0-9+.]*=(\"[^\"]*\"|[0-9]*)").matcher(line);
                PDU pdu = new PDU();


                while (matcher.find()) {
                    String token = matcher.group();
                    String oId = token.substring(2,token.indexOf("="));
                    String value = token.substring(token.indexOf("=")+1);
                    value = value.replace("\"", "");
                    pdu.add(new VariableBinding(new OID("1.3.6.1.4.1." + oId), new OctetString(value)));

                }

                pdu.setType(PDU.NOTIFICATION);

//                System.out.println(pdu);
                sendPDURequestScheduling.sendPDU(pdu);
//                String string=pdu.get(2).toString();
//                System.out.println(counter);
//                counter++;

            }

            System.out.println(new Date());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

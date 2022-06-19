package com.example.simualtor.config;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class OIDMapTranslator {

    public final static Map<String, String> OIDMap = new HashMap<>();

    public static Map<String, String> getOIDMap() {
        return OIDMap;
    }


    static {
//        l
//        2011.2.15.2.4.3.3.1
        OIDMap.put("alarmRestoreTime", "2011.2.15.2.4.3.3.15.0");
        OIDMap.put("alarmOperator", "2011.2.15.2.4.3.3.16.0");
        OIDMap.put("alarmServiceAffectFlag", "2011.2.15.2.4.3.3.50.0");
        OIDMap.put("alarmLevel", "2011.2.15.2.4.3.3.11.0");
        OIDMap.put("alarmRestore", "2011.2.15.2.4.3.3.12.0");
        OIDMap.put("alarmAckTime", "2011.2.15.2.4.3.3.14.0");
        OIDMap.put("alarmSpecificProblems", "2011.2.15.2.4.3.3.30.0");
        OIDMap.put("alarmAdditionalInfo", "2011.2.15.2.4.3.3.51.0");
        OIDMap.put("alarmType", "2011.2.15.2.4.3.3.10.0");
        OIDMap.put("alarmMOName", "2011.2.15.2.4.3.3.4.0");
        OIDMap.put("alarmProductID", "2011.2.15.2.4.3.3.5.0");
        OIDMap.put("alarmNEType", "2011.2.15.2.4.3.3.6.0");
        OIDMap.put("alarmNEDevID", "2011.2.15.2.4.3.3.7.0");
        OIDMap.put("alarmExtendInfo", "2011.2.15.2.4.3.3.27.0");
        OIDMap.put("alarmCategory", "2011.2.15.2.4.3.3.2.0");
        OIDMap.put("alarmProposedRepairActions", "2011.2.15.2.4.3.3.29.0");
        OIDMap.put("alarmClearCategory", "2011.2.15.2.4.3.3.48.0");
        OIDMap.put("alarmProbableCause", "2011.2.15.2.4.3.3.28.0");
        OIDMap.put("alarmClearType", "2011.2.15.2.4.3.3.49.0");
        OIDMap.put("alarmClearOperator", "2011.2.15.2.4.3.3.46.0");
        OIDMap.put("alarmObjectInstanceType", "2011.2.15.2.4.3.3.47.0");
        OIDMap.put("alarmDevCsn", "2011.2.15.2.4.3.3.8.0");
        OIDMap.put("alarmID", "2011.2.15.2.4.3.3.9.0");
        OIDMap.put("alarmConfirm", "2011.2.15.2.4.3.3.13.0");
        OIDMap.put("AlarmOccurTime","2011.2.15.2.4.3.3.3.0");
    }

}

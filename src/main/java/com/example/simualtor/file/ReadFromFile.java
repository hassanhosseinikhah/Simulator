package com.example.simualtor.file;

import com.example.simualtor.config.OIDMapTranslator;
import com.example.simualtor.config.Patterns;
import com.example.simualtor.enums.AlarmFieldEnum;
import com.example.simualtor.service.SendToKafkaRequestScheduling;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Matcher;

@Component
@Slf4j
@RequiredArgsConstructor
public class ReadFromFile {

    @Value("${variables.file.path}")
    private String filePath;
    Map<String, String> alarmOIDMapValues = new ConcurrentHashMap<>();
    Map<AlarmFieldEnum, String> alarmEnumMap = new ConcurrentHashMap<>();
    private final SendToKafkaRequestScheduling sendToKafkaRequestScheduling;

    public void read() {
        log.info("start processing file");
        try (Scanner scanner = new Scanner(new File(filePath))) {
            while (scanner.hasNext()) {
                String line = "";
                String alarmEndTrapIndicator = "}";
                do {
                    if (!scanner.hasNextLine())
                        break;
                    line = scanner.nextLine();
                } while (!line.contains(alarmEndTrapIndicator));
                Matcher matcherOIDVALUE = Patterns.OID_VALUE.matcher(line);
                while (matcherOIDVALUE.find()) {
                    String token = matcherOIDVALUE.group();
                    String oId = token.substring(2, token.indexOf("="));
                    String value = token.substring(token.indexOf("=") + 1);
                    value = value.replace("\"", "");
                    Matcher matcherTime = Patterns.TIME.matcher(value);
                    if (matcherTime.find()) {
                        value = LocalDate.now() + " " + matcherTime.group(2);
                    }
                    alarmOIDMapValues.put(oId, value);
                }
                sendToKafkaRequestScheduling.sendToKafka(translate(alarmOIDMapValues), alarmOIDMapValues);
            }
            log.info("end proceeding file");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Map<AlarmFieldEnum, String> translate(Map<String, String> alarmOIDMapValues) {

        if (!(isAfterNow(alarmOIDMapValues.get(OIDMapTranslator.getOIDMap().get("AlarmOccurTime"))))) {
            String date = alarmOIDMapValues.get(OIDMapTranslator.getOIDMap().get("AlarmOccurTime"));
            int digit = Character.getNumericValue(date.charAt(9)) + 1;
            String newDate = date.substring(0, 9) + digit + date.substring(10);
            alarmOIDMapValues.put(OIDMapTranslator.getOIDMap().get("AlarmOccurTime"), newDate);
        }
        return createAlarmFieldEnumMap(alarmOIDMapValues);
    }

    private boolean isAfterNow(String alarmOccurTime) {
        LocalTime timestamp = LocalTime.parse(alarmOccurTime.substring(11), DateTimeFormatter.ofPattern("HH:mm:ss"));
        return timestamp.isAfter(LocalTime.now());

    }

    private Map<AlarmFieldEnum, String> createAlarmFieldEnumMap(Map<String, String> alarmOIDMapValues) {
        alarmEnumMap.put(AlarmFieldEnum.getEnum("alarmRestoreTime"), alarmOIDMapValues.get(OIDMapTranslator.getOIDMap().get("alarmRestoreTime")));
        alarmEnumMap.put(AlarmFieldEnum.getEnum("alarmOperator"), alarmOIDMapValues.get(OIDMapTranslator.getOIDMap().get("alarmOperator")));
        alarmEnumMap.put(AlarmFieldEnum.getEnum("alarmServiceAffectFlag"), alarmOIDMapValues.get(OIDMapTranslator.getOIDMap().get("alarmServiceAffectFlag")));
        alarmEnumMap.put(AlarmFieldEnum.getEnum("alarmLevel"), alarmOIDMapValues.get(OIDMapTranslator.getOIDMap().get("alarmLevel")));
        alarmEnumMap.put(AlarmFieldEnum.getEnum("alarmRestore"), alarmOIDMapValues.get(OIDMapTranslator.getOIDMap().get("alarmRestore")));
        alarmEnumMap.put(AlarmFieldEnum.getEnum("alarmAckTime"), alarmOIDMapValues.get(OIDMapTranslator.getOIDMap().get("alarmAckTime")));
        alarmEnumMap.put(AlarmFieldEnum.getEnum("alarmSpecificProblems"), alarmOIDMapValues.get(OIDMapTranslator.getOIDMap().get("alarmSpecificProblems")));
        alarmEnumMap.put(AlarmFieldEnum.getEnum("alarmAdditionalInfo"), alarmOIDMapValues.get(OIDMapTranslator.getOIDMap().get("alarmAdditionalInfo")));
        alarmEnumMap.put(AlarmFieldEnum.getEnum("alarmType"), alarmOIDMapValues.get(OIDMapTranslator.getOIDMap().get("alarmType")));
        alarmEnumMap.put(AlarmFieldEnum.getEnum("alarmMOName"), alarmOIDMapValues.get(OIDMapTranslator.getOIDMap().get("alarmMOName")));
        alarmEnumMap.put(AlarmFieldEnum.getEnum("alarmProductID"), alarmOIDMapValues.get(OIDMapTranslator.getOIDMap().get("alarmProductID")));
        alarmEnumMap.put(AlarmFieldEnum.getEnum("alarmNEType"), alarmOIDMapValues.get(OIDMapTranslator.getOIDMap().get("alarmNEType")));
        alarmEnumMap.put(AlarmFieldEnum.getEnum("alarmNEDevID"), alarmOIDMapValues.get(OIDMapTranslator.getOIDMap().get("alarmNEDevID")));
        alarmEnumMap.put(AlarmFieldEnum.getEnum("alarmExtendInfo"), alarmOIDMapValues.get(OIDMapTranslator.getOIDMap().get("alarmExtendInfo")));
        alarmEnumMap.put(AlarmFieldEnum.getEnum("alarmCategory"), alarmOIDMapValues.get(OIDMapTranslator.getOIDMap().get("alarmCategory")));
        alarmEnumMap.put(AlarmFieldEnum.getEnum("alarmProposedRepairActions"), alarmOIDMapValues.get(OIDMapTranslator.getOIDMap().get("alarmProposedRepairActions")));
        alarmEnumMap.put(AlarmFieldEnum.getEnum("alarmClearCategory"), alarmOIDMapValues.get(OIDMapTranslator.getOIDMap().get("alarmClearCategory")));
        alarmEnumMap.put(AlarmFieldEnum.getEnum("alarmProbableCause"), alarmOIDMapValues.get(OIDMapTranslator.getOIDMap().get("alarmProbableCause")));
        alarmEnumMap.put(AlarmFieldEnum.getEnum("alarmClearType"), alarmOIDMapValues.get(OIDMapTranslator.getOIDMap().get("alarmClearType")));
        alarmEnumMap.put(AlarmFieldEnum.getEnum("alarmClearOperator"), alarmOIDMapValues.get(OIDMapTranslator.getOIDMap().get("alarmClearOperator")));
        alarmEnumMap.put(AlarmFieldEnum.getEnum("alarmObjectInstanceType"), alarmOIDMapValues.get(OIDMapTranslator.getOIDMap().get("alarmObjectInstanceType")));
        alarmEnumMap.put(AlarmFieldEnum.getEnum("alarmDevCsn"), alarmOIDMapValues.get(OIDMapTranslator.getOIDMap().get("alarmDevCsn")));
        alarmEnumMap.put(AlarmFieldEnum.getEnum("alarmID"), alarmOIDMapValues.get(OIDMapTranslator.getOIDMap().get("alarmID")));
        alarmEnumMap.put(AlarmFieldEnum.getEnum("alarmConfirm"), alarmOIDMapValues.get(OIDMapTranslator.getOIDMap().get("alarmConfirm")));
        alarmEnumMap.put(AlarmFieldEnum.getEnum("AlarmOccurTime"), alarmOIDMapValues.get(OIDMapTranslator.getOIDMap().get("AlarmOccurTime")));
        return alarmEnumMap;

    }
}
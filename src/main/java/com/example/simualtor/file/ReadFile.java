//package com.example.simualtor.file;
//
//import com.example.simualtor.DTO.AlarmDto;
//import com.example.simualtor.service.SendToKafkaRequestScheduling;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.stereotype.Component;
//
//import java.io.File;
//import java.io.IOException;
//import java.time.LocalDate;
//import java.time.LocalTime;
//import java.time.format.DateTimeFormatter;
//import java.util.Map;
//import java.util.Scanner;
//import java.util.concurrent.ConcurrentHashMap;
//import java.util.regex.Matcher;
//import java.util.regex.Pattern;
//
//
//@Component
//@Slf4j
//public class ReadFile {
//    private final SendToKafkaRequestScheduling sendToKafkaRequestScheduling;
//
//    public ReadFile(SendToKafkaRequestScheduling sendToKafkaRequestScheduling) {
//        this.sendToKafkaRequestScheduling = sendToKafkaRequestScheduling;
//    }
//
//    public static void main(String[] args) {
//
//        String line = "";
//        try {
//            Scanner scanner = new Scanner(new File("F:\\Clarity\\u2k2.txt"));
//            while (scanner.hasNext()) {
//                Map<String, String> alarmOIDMapValues = new ConcurrentHashMap<>();
//
//                do {
//                    if (!scanner.hasNextLine())
//                        break;
//                    line = scanner.nextLine();
//                    line += "\n";
//
//                } while (!line.contains("}"));
//                Matcher matcher = Pattern.compile("E:[0-9+.]*=(\"[^\"]*\"|[0-9]*)").matcher(line);
//                while (matcher.find()) {
//                    String token = matcher.group();
//                    String oId = token.substring(2, token.indexOf("="));
//                    String value = token.substring(token.indexOf("=") + 1);
//                    value = value.replace("\"", "");
//                    Matcher matcher1 = Pattern.compile("(\\d{4}-\\d{2}-\\d{2}) (\\d{2}:\\d{2}:\\d{2})").matcher(value);
//                    if (matcher1.find()) {
//                        value = LocalDate.now()+" "+ matcher1.group(2);
//                    }
//                    alarmOIDMapValues.put(oId, value);
//
//                }
//
//                AlarmDto alarmDto = new AlarmDto(alarmOIDMapValues);
//                if(alarmOIDMapValues.get("2011.2.15.2.4.3.3.3.0")!=null){
//                    LocalTime timestamp = LocalTime.parse(alarmOIDMapValues.get("2011.2.15.2.4.3.3.3.0").substring(11), DateTimeFormatter.ofPattern("HH:mm:ss"));
//                    LocalTime now = LocalTime.now();
//                    if (timestamp.isAfter(now)) {
//                        String string =alarmOIDMapValues.toString();
//                        System.out.println(string.replace('=',':')+"\n");
////                        sendToKafkaRequestScheduling.sendToKafka(str);
//                    }
//                }}
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//    }
//}

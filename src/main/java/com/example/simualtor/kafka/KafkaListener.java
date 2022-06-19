//package com.example.simualtor.kafka;
//
//
//import com.google.gson.Gson;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.kafka.annotation.PartitionOffset;
//import org.springframework.stereotype.Component;
//
//import java.util.Map;
//
//
//@Component
//@Slf4j
//public class KafkaListener {
//
//    private final Gson gson;
//
//    public KafkaListener(Gson gson) {
//        this.gson = gson;
//    }
//
//
//    //    @org.springframework.kafka.annotation.KafkaListener(topics = "AlarmSimulator" ,  groupId = "1")
//
//    @org.springframework.kafka.annotation.KafkaListener(topicPartitions = @org.springframework.kafka.annotation.TopicPartition(topic = "AlarmFieldEnum7",
//            partitionOffsets = {@PartitionOffset(partition = "0", initialOffset = "-1")}), groupId = "4")
//    void listener(String data) {
//        log.info("Start Listener From Topic");
//        System.out.println("\n " + gson.fromJson(data, Map.class) + "\n");
//    }
//
//
//}
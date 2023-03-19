package com.niovo.niovokafka.service;

import com.niovo.niovokafka.util.AppConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class KafkaConsumer {

    @KafkaListener(topics = AppConstants.TOPIC_NAME_USER, groupId = AppConstants.GROUP_ID)
    public void consumeUser(String user) {

        log.info(String.format("User Message received -> %s", user));
    }

    @KafkaListener(topics = AppConstants.TOPIC_NAME_TASK, groupId = AppConstants.GROUP_ID)
    public void consumeTask(String task) {

        log.info(String.format("Task Message received -> %s", task));
    }
}
package com.niovo.niovokafka.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import static com.niovo.niovokafka.util.AppConstants.GROUP_ID;
import static com.niovo.niovokafka.util.AppConstants.TOPIC_NAME_TASK;
import static com.niovo.niovokafka.util.AppConstants.TOPIC_NAME_USER;

@Service
@Slf4j
public class KafkaConsumer {

    @KafkaListener(topics = TOPIC_NAME_USER, groupId = GROUP_ID)
    public void consumeUser(String user) {

        log.info(String.format("User Message received -> %s", user));
    }

    @KafkaListener(topics = TOPIC_NAME_TASK, groupId = GROUP_ID)
    public void consumeTask(String task) {

        log.info(String.format("Task Message received -> %s", task));
    }
}
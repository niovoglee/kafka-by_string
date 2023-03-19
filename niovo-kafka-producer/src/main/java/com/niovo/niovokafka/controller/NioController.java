package com.niovo.niovokafka.controller;

import com.niovo.niovokafka.dto.User;
import com.niovo.niovokafka.dto.tesk.TaskInput;
import com.niovo.niovokafka.dto.tesk.TaskItemResponse;
import com.niovo.niovokafka.service.KafkaProducer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.Random;

import static com.niovo.niovokafka.resources.NiovoResource.PUBLISH;
import static com.niovo.niovokafka.resources.NiovoResource.TASK;
import static com.niovo.niovokafka.resources.NiovoResource.USER;
import static com.niovo.niovokafka.util.AppConstants.TOPIC_NAME_TASK;
import static com.niovo.niovokafka.util.AppConstants.TOPIC_NAME_USER;

@RestController
@Slf4j
@RequestMapping(value = PUBLISH)
public class NioController {

    final KafkaProducer kafkaProducer;

    public NioController(KafkaProducer kafkaProducer) {

        this.kafkaProducer = kafkaProducer;
    }

    @PostMapping(value = USER, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> sendUserMessage(@RequestBody User user) {

        log.info("Nio Produce User {}", user);
        kafkaProducer.sendUserMessage(user, TOPIC_NAME_USER);
        return ResponseEntity.ok(user);
    }

    @PostMapping(value = TASK, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TaskItemResponse> sendTaskMessage(@RequestBody TaskInput taskInput) {

        log.info("Nio Produce Task {}", taskInput);
        kafkaProducer.sendTaskMessage(taskInput, TOPIC_NAME_TASK);
        return ResponseEntity.ok(doSomeTaskProcess(taskInput));
    }

    private TaskItemResponse doSomeTaskProcess(TaskInput taskInput) {

        return TaskItemResponse
                .builder()
                .id(String.valueOf(new Random().nextInt()))
                .title(taskInput.getTitle())
                .description(taskInput.getDescription())
                .status(taskInput.getStatus())
                .isImportant(taskInput.isImportant())
                .dueDate(LocalDateTime.parse(taskInput.getDueDate()))
                .userId(taskInput.getUserId())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now().plusDays(7))
                .build();
    }
}
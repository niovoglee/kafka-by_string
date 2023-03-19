package com.niovo.niovokafka.controller.task;

import com.niovo.niovokafka.dto.User;
import com.niovo.niovokafka.dto.tesk.TaskInput;
import com.niovo.niovokafka.dto.tesk.TaskItemResponse;
import com.niovo.niovokafka.service.KafkaProducer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.Random;

import static com.niovo.niovokafka.util.AppConstants.TOPIC_NAME_TASK;
import static com.niovo.niovokafka.util.AppConstants.TOPIC_NAME_USER;

@RestController
@Slf4j
@RequestMapping(value = "/tesk")
public class TaskController implements CommandLineRunner {

    final KafkaProducer kafkaProducer;

    final RestTemplate restTemplate;

    public TaskController(KafkaProducer kafkaProducer, RestTemplate restTemplate) {

        this.kafkaProducer = kafkaProducer;
        this.restTemplate = restTemplate;
    }

    @PostMapping("/create-task")
    public ResponseEntity<TaskItemResponse> createTask() {

        log.info("Nio Produce Task");
        TaskInput taskInput = createTaskInput();

        HttpHeaders headers = createHttpHeaders();

        HttpEntity<TaskInput> request = new HttpEntity<>(taskInput, headers);
        String url = "/tasks/create";

        ResponseEntity<TaskItemResponse> result = restTemplate.postForEntity(url, request, TaskItemResponse.class);
        TaskItemResponse task = result.getBody();

        assert task != null;

        return ResponseEntity.ok().body(task);
    }

    @Override
    public void run(String... args) {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        log.info("EXECUTING : command line runner");

        kafkaProducer.sendUserMessage(createUser(), TOPIC_NAME_USER);
        kafkaProducer.sendTaskMessage(createTaskInput(), TOPIC_NAME_TASK);
    }

    public User createUser() {

        return User.builder().id(new Random().nextInt()).lastName("Ajokpaniovo").firstName("Jonathan").build();
    }

    public TaskInput createTaskInput() {

        TaskInput taskInput = new TaskInput();

        taskInput.setTitle("My task one");
        taskInput.setDescription("Description of my task one");
        taskInput.setStatus("Pending");
        taskInput.setImportant(true);
        taskInput.setDueDate(LocalDateTime.now().plusDays(7).toString());
        taskInput.setUserId("632f2d1c7e05bb050cdda4cb");
        return taskInput;
    }

    private HttpHeaders createHttpHeaders() {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("x-access-token",
                    "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6IjYzMmYyZDFjN2UwNWJiMDUwY2RkYTRjYiIsImlhdCI6MTY2NDcxMTI1OSwiZXhwIjoxNjY0NzExMzQ1fQ.4nI9KCat1GJZaMp1RkRHhn99STBg17RA6EwLknMxDO4");
        return headers;
    }
}
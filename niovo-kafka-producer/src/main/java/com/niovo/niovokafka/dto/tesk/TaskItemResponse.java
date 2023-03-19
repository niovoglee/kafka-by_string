package com.niovo.niovokafka.dto.tesk;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(builderClassName = "Builder", toBuilder = true)
public class TaskItemResponse {
    @JsonProperty(required = true)
    private String id;

    @JsonProperty(required = true)
    private String title;

    private String description;

    @JsonProperty(required = true)
    private String status;

    @JsonProperty(value = "isimportant", required = true)
    private boolean isImportant;

    @JsonProperty(value = "date", required = true)
    private LocalDateTime dueDate;

    @JsonProperty(value = "user", required = true)
    private String userId;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
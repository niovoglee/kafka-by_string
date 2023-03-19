package com.niovo.niovokafka.dto.tesk;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(builderClassName = "Builder", toBuilder = true)
public class TaskInput {

    @JsonProperty(required = true)
    private String title;

    @JsonProperty(required = true)
    private String description;

    @JsonProperty(required = true)
    private String status;

    @JsonProperty(value = "is_important", required = true)
    private boolean isImportant;

    @JsonProperty(value = "date", required = true)
    private String dueDate;

    @JsonProperty(value = "user", required = true)
    private String userId;
}


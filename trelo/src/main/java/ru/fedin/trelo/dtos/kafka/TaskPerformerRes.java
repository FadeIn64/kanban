package ru.fedin.trelo.dtos.kafka;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TaskPerformerRes {
    @JsonIgnore
    private Integer id;

    @NotNull
    private DeskContributorRes contributor;

    @NotNull
    @JsonIgnore
    private Integer task;

}
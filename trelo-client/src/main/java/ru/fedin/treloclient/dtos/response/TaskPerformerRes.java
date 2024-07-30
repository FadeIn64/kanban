package ru.fedin.treloclient.dtos.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
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
package ru.fedin.treloclient.dtos.requests;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TaskPerformerReq {
    @JsonIgnore
    private Integer id;

    @NotNull
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private DeskContributorReq contributor;

    @NotNull
    @JsonIgnore
    private Integer task;

}
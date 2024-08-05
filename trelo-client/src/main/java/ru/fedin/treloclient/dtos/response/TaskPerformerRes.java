package ru.fedin.treloclient.dtos.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TaskPerformerRes {
    @JsonIgnore
    private Integer id;

    @NotNull
    private DeskContributorRes contributor;

    @NotNull
    @JsonIgnore
    private Integer task;

}
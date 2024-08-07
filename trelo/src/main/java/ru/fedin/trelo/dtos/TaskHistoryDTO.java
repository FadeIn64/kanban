package ru.fedin.trelo.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TaskHistoryDTO {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Integer id;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private DeskTaskDTO task;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private DeskColumnDTO columnFrom;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private DeskColumnDTO columnTo;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private LocalDateTime changeDate;

}

package ru.fedin.treloclient.dtos.requests;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class TaskHistoryReq {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Integer id;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private DeskTaskReq task;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private DeskColumnReq columnFrom;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private DeskColumnReq columnTo;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private LocalDateTime changeDate;

}

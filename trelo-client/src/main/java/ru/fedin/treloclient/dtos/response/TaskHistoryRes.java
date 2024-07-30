package ru.fedin.treloclient.dtos.response;

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
public class TaskHistoryRes {

    private Integer id;
    private DeskTaskRes task;
    private DeskColumnRes columnFrom;
    private DeskColumnRes columnTo;
    private LocalDateTime changeDate;

}

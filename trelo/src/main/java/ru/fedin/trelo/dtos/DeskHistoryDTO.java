package ru.fedin.trelo.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class DeskHistoryDTO {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Integer id;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private DeskTaskDTO task;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private DeskColumnDTO from;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private DeskColumnDTO to;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private LocalDate date;

}

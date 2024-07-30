package ru.fedin.treloclient.dtos.requests;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.fedin.treloclient.dtos.enums.Importance;

import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class DeskTaskReq {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Integer id;
    private Integer desk;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Integer column;
    private String header;
    private String description;
//    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String author;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private List<TaskPerformerReq> performers;
    private Importance importance;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private LocalDateTime createDate;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private double coast;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private List<FileReq> files;

}

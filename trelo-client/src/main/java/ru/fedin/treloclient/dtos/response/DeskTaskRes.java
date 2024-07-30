package ru.fedin.treloclient.dtos.response;

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
public class DeskTaskRes {


    private Integer id;
    private Integer desk;

    private Integer column;
    private String header;
    private String description;
//    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String author;

    private List<TaskPerformerRes> performers;
    private Importance importance;

    private LocalDateTime createDate;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private double coast;

    private List<FileRes> files;

}
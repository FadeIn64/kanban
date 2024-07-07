package ru.fedin.trelo.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.fedin.trelo.eintites.enums.Importance;

import java.time.LocalDate;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class DeskTaskDTO {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Integer id;
    private Integer desk;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Integer column;
    private String header;
    private String description;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String author;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private List<TaskPerformerDTO> performers;
    private Importance importance;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private LocalDate createDate;
    private LocalDate startDate;
    private LocalDate endDate;
    private double coast;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String file;

}

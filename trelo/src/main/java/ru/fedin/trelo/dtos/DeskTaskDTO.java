package ru.fedin.trelo.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.fedin.trelo.dtos.minio.FileDTO;
import ru.fedin.trelo.eintites.enums.Importance;
import ru.fedin.trelo.validation.DataIntervalCheck;

import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Valid
@DataIntervalCheck(
        startDate = "startDate",
        endDate = "endDate"
)
public class DeskTaskDTO {

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
    private List<DeskContributorDTO> performers;
    private Importance importance;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private LocalDateTime createDate;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private double coast;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private List<FileDTO> files;

}

package ru.fedin.treloclient.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.fedin.treloclient.models.enums.Importance;
import ru.fedin.treloclient.validation.DataIntervalCheck;


import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@DataIntervalCheck(
        startDate = "startDate",
        endDate = "endDate"
)
public class TaskDto implements Serializable {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;
    @NotNull
    private String header;
    @NotNull
    private String description;
    @NotNull
    private Importance importance;

    private BigDecimal coast = new BigDecimal(0);
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private LocalDateTime createDate;
    @NotNull
    private LocalDateTime startDate;
    @NotNull
    private LocalDateTime endDate;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private UserDto performer;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private long deskId;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private long columnId;

    private List<FileDto> files = new ArrayList<>();
}
package ru.fedin.trelorefactor.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import ru.fedin.trelorefactor.eintites.enums.Importance;
import ru.fedin.trelorefactor.validation.DataIntervalCheck;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * DTO for {@link ru.fedin.trelorefactor.eintites.Task}
 */
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
}
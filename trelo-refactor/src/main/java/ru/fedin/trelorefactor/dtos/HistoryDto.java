package ru.fedin.trelorefactor.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * DTO for {@link ru.fedin.trelorefactor.eintites.History}
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class HistoryDto implements Serializable {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;
    @NotNull
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private LocalDateTime changeDate;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private TaskDto task;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private ColumnDto column;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private UserDto user;
}
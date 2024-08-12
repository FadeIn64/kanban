package ru.fedin.trelorefactor.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.io.Serializable;
import java.util.List;

/**
 * DTO for {@link ru.fedin.trelorefactor.eintites.Desk}
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class DeskDto implements Serializable {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;
    @NotNull
    private String name;
    @NotNull
    private UserDto author;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private List<ColumnDto> columns;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private List<UserDto> users;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private List<TaskDto> tasks;
}
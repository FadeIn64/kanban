package ru.fedin.trelorefactor.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.io.Serializable;

/**
 * DTO for {@link ru.fedin.trelorefactor.eintites.ColumnEntity}
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class ColumnDto implements Serializable {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;
    @NotNull
    private String name;
    @NotNull
    private Integer order;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long deskId;
}
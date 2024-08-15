package ru.fedin.trelorefactor.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * DTO for {@link ru.fedin.trelorefactor.eintites.ColumnEntity}
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Valid
public class ColumnDto implements Serializable {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;
    @NotNull
    private String name;
    @NotNull
    @Positive
    private Integer order;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long deskId;
}
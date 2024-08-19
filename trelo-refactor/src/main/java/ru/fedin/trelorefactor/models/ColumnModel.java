package ru.fedin.trelorefactor.models;

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
public class ColumnModel implements Serializable {

    private Long id;
    @NotNull
    private String name;
    @NotNull
    @Positive
    private Integer order;

    private Long deskId;
}
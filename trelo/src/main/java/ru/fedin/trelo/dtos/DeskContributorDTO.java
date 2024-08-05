package ru.fedin.trelo.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeskContributorDTO {

    @JsonIgnore
    private Integer id;

    @NotNull
    @JsonIgnore
    private Integer desk;

    @NotNull
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String contributor;

}
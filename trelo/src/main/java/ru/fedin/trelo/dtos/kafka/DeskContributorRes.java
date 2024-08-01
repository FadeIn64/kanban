package ru.fedin.trelo.dtos.kafka;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DeskContributorRes {

    @JsonIgnore
    private Integer id;

    @NotNull
    @JsonIgnore
    private Integer desk;

    @NotNull
    private String contributor;

}
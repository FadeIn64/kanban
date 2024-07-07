package ru.fedin.trelo.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class DeskColumnDTO {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Integer id;

    private Integer desk;

    private String name;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Integer next;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Integer prev;
}

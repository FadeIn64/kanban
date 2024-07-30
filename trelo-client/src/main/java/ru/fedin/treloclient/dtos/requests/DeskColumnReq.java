package ru.fedin.treloclient.dtos.requests;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class DeskColumnReq {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Integer id;

    private Integer desk;

    private String name;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Integer next;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Integer prev;
}

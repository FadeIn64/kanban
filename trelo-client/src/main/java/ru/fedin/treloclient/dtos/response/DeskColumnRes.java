package ru.fedin.treloclient.dtos.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class DeskColumnRes {


    private Integer id;

    private Integer desk;

    private String name;


    private Integer next;


    private Integer prev;
}

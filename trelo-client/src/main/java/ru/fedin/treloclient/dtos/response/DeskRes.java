package ru.fedin.treloclient.dtos.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class DeskRes {

    private Integer id;

    private String name;

//    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String author;

    private List<DeskColumnRes> deskColumns = new ArrayList<>();

    private List<DeskContributorRes> deskContributors = new ArrayList<>();

    private List<DeskTaskRes> deskTasks = new ArrayList<>();


}

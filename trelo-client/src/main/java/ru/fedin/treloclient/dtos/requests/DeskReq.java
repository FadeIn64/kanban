package ru.fedin.treloclient.dtos.requests;

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
public class DeskReq {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Integer id;

    private String name;

//    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String author;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private List<DeskColumnReq> deskColumns = new ArrayList<>();

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private List<DeskContributorReq> deskContributors = new ArrayList<>();

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private List<DeskTaskReq> deskTasks = new ArrayList<>();


}

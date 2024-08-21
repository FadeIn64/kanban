package ru.fedin.treloclient.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class DeskDto implements Serializable {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;
    @NotNull
    private String name;
    @NotNull
    private UserDto author;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private List<ColumnDto> columns = new ArrayList<>();
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private List<UserDto> users = new ArrayList<>();
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private List<TaskDto> tasks = new ArrayList<>();
}
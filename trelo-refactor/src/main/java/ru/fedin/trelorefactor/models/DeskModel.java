package ru.fedin.trelorefactor.models;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * DTO for {@link ru.fedin.trelorefactor.eintites.Desk}
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class DeskModel implements Serializable {

    private Long id;
    @NotNull
    private String name;
    @NotNull
    private UserModel author;

    private List<ColumnModel> columns = new ArrayList<>();

    private List<UserModel> users = new ArrayList<>();

    private List<TaskModel> tasks = new ArrayList<>();
}
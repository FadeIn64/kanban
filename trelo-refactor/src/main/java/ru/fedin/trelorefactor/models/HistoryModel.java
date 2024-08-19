package ru.fedin.trelorefactor.models;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * DTO for {@link ru.fedin.trelorefactor.eintites.History}
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class HistoryModel implements Serializable {

    private Long id;
    @NotNull

    private LocalDateTime changeDate;

    private TaskModel task;

    private ColumnModel column;

    private UserModel user;
}
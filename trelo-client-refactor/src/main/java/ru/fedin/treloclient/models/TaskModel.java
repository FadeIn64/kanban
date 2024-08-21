package ru.fedin.treloclient.models;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.fedin.treloclient.models.enums.Importance;
import ru.fedin.treloclient.validation.DataIntervalCheck;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;


@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@DataIntervalCheck(
        startDate = "startDate",
        endDate = "endDate"
)
public class TaskModel implements Serializable {

    private Long id;
    @NotNull
    private String header;
    @NotNull
    private String description;
    @NotNull
    private Importance importance;
    private BigDecimal coast = new BigDecimal(0);
    private LocalDateTime createDate;
    @NotNull
    private LocalDateTime startDate;
    @NotNull
    private LocalDateTime endDate;
    private UserModel performer;
    private long deskId;
    private long columnId;
}
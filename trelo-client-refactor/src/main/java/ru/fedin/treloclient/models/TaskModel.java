package ru.fedin.treloclient.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;
import org.springframework.data.redis.core.index.Indexed;
import ru.fedin.treloclient.models.enums.Importance;
import ru.fedin.treloclient.validation.DataIntervalCheck;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@DataIntervalCheck(
        startDate = "startDate",
        endDate = "endDate"
)
@RedisHash("Task")
public class TaskModel implements Serializable {

    @Id
    private Long id;
    @Indexed
    @NotNull
    private String header;
    @NotNull
    @Indexed
    private String description;
    @NotNull
    @Indexed
    private Importance importance;
    @Indexed
    private BigDecimal coast = new BigDecimal(0);
    @Indexed
    private LocalDateTime createDate;
    @Indexed
    @NotNull
    private LocalDateTime startDate;
    @Indexed
    @NotNull
    private LocalDateTime endDate;
    @Indexed
    private UserModel performer;
    @Indexed
    private long deskId;
    @Indexed
    private long columnId;
    private List<FileModel> files;

    @TimeToLive
    @Value("${spring.data.redis.time-to-live}")
    @JsonIgnore
    private long ttl;
}
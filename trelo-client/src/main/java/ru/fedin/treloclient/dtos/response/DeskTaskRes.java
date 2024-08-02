package ru.fedin.treloclient.dtos.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import ru.fedin.treloclient.dtos.enums.Importance;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@RedisHash("Task")
public class DeskTaskRes {
    @Id
    private Integer id;
    private Integer desk;

    private Integer column;
    private String header;
    private String description;
//    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String author;

    private List<TaskPerformerRes> performers = new ArrayList<>();
    private Importance importance;

    private LocalDateTime createDate;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private double coast;

    private List<FileRes> files = new ArrayList<>();

}

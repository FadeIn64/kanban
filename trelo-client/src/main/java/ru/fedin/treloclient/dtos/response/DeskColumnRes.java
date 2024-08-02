package ru.fedin.treloclient.dtos.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@RedisHash("Column")
public class DeskColumnRes {

    @Id
    private Integer id;

    private Integer desk;

    private String name;

    private Integer next;

    private Integer prev;

    @TimeToLive
    @JsonIgnore
    @Value("${spring.data.redis.time-to-live}")
    private long ttl;
}

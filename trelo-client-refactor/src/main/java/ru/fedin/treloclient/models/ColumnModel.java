package ru.fedin.treloclient.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;

import java.io.Serializable;


@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Valid
@RedisHash("Column")
public class ColumnModel implements Serializable {

    @Id
    private Long id;
    @NotNull
    private String name;
    @NotNull
    @Positive
    private Integer order;

    private Long deskId;

    @TimeToLive
    @Value("${spring.data.redis.time-to-live}")
    @JsonIgnore
    private long ttl;
}
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


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@RedisHash("Desk")
public class DeskModel implements Serializable {

    @Id
    private Long id;
    @NotNull
    private String name;
    @NotNull
    private UserModel author;

    private List<ColumnModel> columns = new ArrayList<>();

    private List<UserModel> users = new ArrayList<>();

    private List<TaskModel> tasks = new ArrayList<>();

    @TimeToLive
    @Value("${spring.data.redis.time-to-live}")
    @JsonIgnore
    private long ttl;
}
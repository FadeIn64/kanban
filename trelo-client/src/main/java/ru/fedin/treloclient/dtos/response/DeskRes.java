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

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@RedisHash("Desk")
public class DeskRes {

    @Id
    private Integer id;

    private String name;

    private String author;

    private List<DeskColumnRes> deskColumns = new ArrayList<>();

    private List<DeskContributorRes> deskContributors = new ArrayList<>();

    private List<DeskTaskRes> deskTasks = new ArrayList<>();

    @TimeToLive
    @JsonIgnore
    @Value("${spring.data.redis.time-to-live}")
    private long ttl;
}

package ru.fedin.treloclient.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@RedisHash("User")
public class UserModel {
    @Id
    Long id;
    String name;
    String email;

    @TimeToLive
    @Value("${spring.data.redis.time-to-live}")
    @JsonIgnore
    private long ttl;
}
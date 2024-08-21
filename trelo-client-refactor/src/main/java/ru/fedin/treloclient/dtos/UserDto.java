package ru.fedin.treloclient.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto{
    Long id;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    String name;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    String email;
}
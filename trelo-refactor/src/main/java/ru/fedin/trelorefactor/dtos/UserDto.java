package ru.fedin.trelorefactor.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;


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
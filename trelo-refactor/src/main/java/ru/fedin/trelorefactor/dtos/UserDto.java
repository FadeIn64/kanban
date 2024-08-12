package ru.fedin.trelorefactor.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.io.Serializable;


@Value
@Builder
public class UserDto{
    Long id;
    @NotNull
    String name;
    @NotNull
    @Email
    String email;
}
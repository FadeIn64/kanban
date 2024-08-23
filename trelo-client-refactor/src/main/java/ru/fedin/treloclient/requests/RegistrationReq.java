package ru.fedin.treloclient.requests;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegistrationReq {
    @NotNull
    private String username;
    @Size(min = 8, max = 32)
    private String password;
    @Email
    private String email;
}

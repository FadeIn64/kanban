package ru.fedin.trelorefactor.messaging;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MessageStatus {
    private Status status;
    private String reason = "no reason";
}

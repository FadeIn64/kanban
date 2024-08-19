package ru.fedin.trelorefactor.messaging;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Message<T, E extends AbstractAction> {

    private E action;
    private T message;
    private MessageStatus status;
}

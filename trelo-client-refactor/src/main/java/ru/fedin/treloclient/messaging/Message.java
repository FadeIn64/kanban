package ru.fedin.treloclient.messaging;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Message<T, E extends AbstractAction> {

    private E action;
    private T message;
    private MessageStatus status;
}

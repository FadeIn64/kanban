package ru.fedin.treloclient.messaging;

import lombok.extern.slf4j.Slf4j;


import java.util.List;

@Slf4j
public enum DeskAction implements AbstractAction {

    CREATE,
    REMOVE,
    RENAME,
    ADD_CONTRIBUTOR,
    REMOVE_CONTRIBUTOR,
    CACHE
}

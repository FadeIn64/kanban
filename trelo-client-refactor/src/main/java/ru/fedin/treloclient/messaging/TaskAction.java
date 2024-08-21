package ru.fedin.treloclient.messaging;


public enum TaskAction implements AbstractAction {
    CREATE,
    CHANGE,
    ChANGE_PERFORMER,
    CHANGE_COLUMN,
    REMOVE,
    CACHE
}

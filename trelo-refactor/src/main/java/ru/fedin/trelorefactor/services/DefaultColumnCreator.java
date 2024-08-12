package ru.fedin.trelorefactor.services;

import ru.fedin.trelorefactor.eintites.ColumnEntity;
import ru.fedin.trelorefactor.eintites.Desk;

import java.util.List;

public interface DefaultColumnCreator {
    List<ColumnEntity> createDefault(Desk desk);
}

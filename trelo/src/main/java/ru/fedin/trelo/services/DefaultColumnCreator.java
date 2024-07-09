package ru.fedin.trelo.services;

import ru.fedin.trelo.eintites.DeskColumn;

import java.util.List;

public interface DefaultColumnCreator {
    List<DeskColumn> createDefault(int deskId);
}

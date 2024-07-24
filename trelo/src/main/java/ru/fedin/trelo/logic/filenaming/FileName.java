package ru.fedin.trelo.logic.filenaming;


public interface FileName {
    String getName(String file, String task);
    String getUrl(String file, String task);
}

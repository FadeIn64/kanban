package ru.fedin.trelo.logic.filenaming;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class FileNameImpl implements FileName{

    @Value(value = "${file-root-url}")
    private String rootUrl;

    @Override
    public String getName(String file, String task) {
        if (file.startsWith(task + "_"))
            return file;
        return task + "_" + file;
    }

    @Override
    public String getUrl(String file, String task) {
        return rootUrl + "/" + task + "/" + getName(file, task);
    }
}

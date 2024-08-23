package ru.fedin.trelorefactor.filenaming;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class FileNameImpl implements FileName{


    @Override
    public String getName(String file, String task) {
        if (file.startsWith(task + "_"))
            return file;
        return task + "_" + file;
    }

    @Override
    public String getUrl(String file, String task) {
        return "/" + task + "/" + getName(file, task);
    }
}

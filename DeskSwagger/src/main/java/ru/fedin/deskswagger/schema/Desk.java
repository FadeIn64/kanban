package ru.fedin.deskswagger.schema;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Desk {

    private Integer id;
    private String name;
    private String author;
    private List<DeskColumn> columns;
    private List<String> contributors;
    private List<DeskTask> tasks;

    public static Desk SAMPLE;

    static {

        List<String> contributors = List.of(new String[]{"user1", "user2", "user3", "user4"});
        SAMPLE = new Desk(1, "Sample", "user1", DeskColumn.DEFAULT, contributors, DeskTask.DEFAULT);
    }
}

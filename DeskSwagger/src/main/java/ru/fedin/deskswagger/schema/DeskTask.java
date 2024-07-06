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
public class DeskTask {

    private Integer id;
    private Integer deskId;
    private Integer columnId;
    private String header;
    private String description;
    private String author;
    private List<String> performers;
    private Importance importance;
    private Date createDate;
    private Date startDate;
    private Date endDate;
    private double coast;
    private String file;

    public static List<DeskTask> DEFAULT;

    static {
        DEFAULT = new ArrayList<>();
        DEFAULT.add(new DeskTask(1, 1, 1, "Task 1", "Task", "user1", List.of("user2"),
                Importance.Low, Calendar.getInstance().getTime(), Calendar.getInstance().getTime(), Calendar.getInstance().getTime(),
                10, "null"));
        DEFAULT.add(new DeskTask(2, 1, 2, "Task 2", "Task", "user1", List.of("user3"),
                Importance.High, Calendar.getInstance().getTime(), Calendar.getInstance().getTime(), Calendar.getInstance().getTime(),
                10, "null"));
        DEFAULT.add(new DeskTask(3, 1, 3, "Task 3", "Task", "user1", List.of("user4"),
                Importance.Medium, Calendar.getInstance().getTime(), Calendar.getInstance().getTime(), Calendar.getInstance().getTime(),
                10, "null"));
    }

}

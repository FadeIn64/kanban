package ru.fedin.deskswagger.schema;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class DeskHistory {
    private Integer id;
    private Integer taskId;
    private Integer from;
    private Integer to;
    private Date date;

    public static List<DeskHistory> DEFAULT;
    static {
        DEFAULT = new ArrayList<>();
        DEFAULT.add(new DeskHistory(1, 1, 1, 2, Calendar.getInstance().getTime()));
        DEFAULT.add(new DeskHistory(2, 1, 2, 3, Calendar.getInstance().getTime()));
        DEFAULT.add(new DeskHistory(3, 1, 3, 2, Calendar.getInstance().getTime()));
    }
}

package ru.fedin.deskswagger.schema;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class DeskColumn {

    private Integer id;
    private Integer deskId;
    private String name;
    private Integer next_id;
    private Integer prev_id;

    public static List<DeskColumn> DEFAULT;
    static {
        DEFAULT = new ArrayList<>();
        DEFAULT.add( new DeskColumn(1, 1, "Надо сделать", 2, 0));
        DEFAULT.add( new DeskColumn(2, 1, "В разработке", 3, 1));
        DEFAULT.add( new DeskColumn(3, 1, "Сделано", 0, 2));
    }
}

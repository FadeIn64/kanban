package ru.fedin.trelo.eintites;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.annotations.Formula;
import ru.fedin.trelo.eintites.enums.Importance;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "desk_task")
public class DeskTask {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @NotNull
    private Integer desk;

    @NotNull
    @Column(name = "header", nullable = false, length = Integer.MAX_VALUE)
    private String header;

    @Column(name = "description", length = Integer.MAX_VALUE)
    private String description;

    @NotNull
    @Column(name = "author", nullable = false, length = Integer.MAX_VALUE)
    private String author;

    @NotNull
    @Column(name = "importance")
    @Enumerated(EnumType.STRING)
    private Importance importance;

    @Column(name = "createdate")
    private LocalDate createDate = LocalDate.now();

    @Column(name = "startdate")
    private LocalDate startDate;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "task")
    private List<TaskPerformer> performers = new ArrayList<>();

    @Column(name = "enddate")
    private LocalDate endDate;

    @Column(name = "coast")
    private Double coast;

    @Column(name = "file", length = Integer.MAX_VALUE)
    private String file;

    @Formula("find_actual_column(id)")
    Integer column;

}
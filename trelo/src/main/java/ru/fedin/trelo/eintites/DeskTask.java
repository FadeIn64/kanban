package ru.fedin.trelo.eintites;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Formula;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

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
    @Column(name = "importance", nullable = false, length = Integer.MAX_VALUE)
    private String importance;

    @Column(name = "createdate")
    private LocalDate createdate = LocalDate.now();

    @Column(name = "startdate")
    private LocalDate startdate;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "task")
    private List<TaskPerformer> performers = new ArrayList<>();

    @Column(name = "enddate")
    private LocalDate enddate;

    @Column(name = "coast")
    private Double coast;

    @Column(name = "file", length = Integer.MAX_VALUE)
    private String file;

    @Formula("find_actual_column(id)")
    Integer column;

}
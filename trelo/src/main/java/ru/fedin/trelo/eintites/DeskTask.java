package ru.fedin.trelo.eintites;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Formula;
import ru.fedin.trelo.eintites.enums.Importance;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "desk_task")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DeskTask {

    // TODO: передалать все на LAZY

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "desk")
    private Desk desk;

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
    private LocalDateTime createDate = LocalDateTime.now();

    @Column(name = "startdate")
    private LocalDateTime startDate;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "task_performers",
            joinColumns = @JoinColumn(name = "task"),
            inverseJoinColumns = @JoinColumn(name = "contributor")
    )
    private List<DeskContributor> performers = new ArrayList<>();

    @Column(name = "enddate")
    private LocalDateTime endDate;

    @Column(name = "coast")
    private Double coast;

    @Formula("find_actual_column(id)")
    private Integer column;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "task")
    private List<TaskFile> files;

}
package ru.fedin.trelorefactor.eintites;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import ru.fedin.trelorefactor.eintites.enums.Importance;
import ru.fedin.trelorefactor.validation.DataIntervalCheck;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "tasks")
@DataIntervalCheck(
        startDate = "startDate",
        endDate = "endDate"
)
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    @EqualsAndHashCode.Include
    private Long id;

    @NotNull
    @Column(name = "header", nullable = false, length = Integer.MAX_VALUE)
    private String header;

    @NotNull
    @Column(name = "description", nullable = false, length = Integer.MAX_VALUE)
    private String description;

    @NotNull
    @Column(name = "importance", nullable = false, length = Integer.MAX_VALUE)
    private Importance importance;

    @Column(name = "coast")
    private BigDecimal coast;

    @NotNull
    @Column(name = "create_date", nullable = false)
    private LocalDateTime createDate;

    @NotNull
    @Column(name = "start_date", nullable = false)
    private LocalDateTime startDate;

    @NotNull
    @Column(name = "end_date", nullable = false)
    private LocalDateTime endDate;


    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false, updatable = false, insertable = false)
    private User performer;

    @Column(name = "user_id")
    private Long userId;


    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "desk_id", nullable = false, updatable = false, insertable = false)
    private Desk desk;

    @Column(name = "desk_id")
    private long deskId;


    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "column_id", nullable = false, updatable = false, insertable = false)
    private ColumnEntity column;

    @Column(name = "column_id")
    private long columnId;

    @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
    @JoinColumn(name = "task_id")
    private List<File> files = new ArrayList<>();

}
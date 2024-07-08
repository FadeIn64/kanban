package ru.fedin.trelo.eintites;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Data
@Entity
@Table(name = "task_performers")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TaskPerformer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "contributor")
    private DeskContributor contributor;

    @NotNull
    private Integer task;

}
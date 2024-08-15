package ru.fedin.trelorefactor.eintites;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;


@Getter
@Setter
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "columns")
public class ColumnEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    @EqualsAndHashCode.Include
    private Long id;

    @NotNull
    @Column(name = "name", nullable = false, length = Integer.MAX_VALUE)
    private String name;

    @NotNull
    @Column(name = "\"order\"", nullable = false)
    private Integer order;


    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "desk_id", nullable = false, updatable = false, insertable = false)
    private Desk desk;

    @Column(name = "desk_id")
    private long deskId;
}
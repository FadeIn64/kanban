package ru.fedin.trelo.eintites;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "desk_contributors")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DeskContributor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    private Integer desk;

    @NotNull
    @Column(name = "contributor", nullable = false, length = Integer.MAX_VALUE)
    private String contributor;

}
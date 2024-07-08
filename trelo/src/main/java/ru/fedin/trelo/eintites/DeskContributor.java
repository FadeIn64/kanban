package ru.fedin.trelo.eintites;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.ArrayList;
import java.util.List;

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
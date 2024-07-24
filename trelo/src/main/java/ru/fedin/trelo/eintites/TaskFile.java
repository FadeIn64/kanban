package ru.fedin.trelo.eintites;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "desk_files")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TaskFile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @Column(name = "description", nullable = false, length = Integer.MAX_VALUE)
    private String description;

    @NotNull
    @Column(name = "url", nullable = false, length = Integer.MAX_VALUE)
    private String url;

    @NotNull
    @Column(name = "size", nullable = false)
    private Long size;

    @NotNull
    @Column(name = "filename", nullable = false, length = Integer.MAX_VALUE)
    private String filename;
}

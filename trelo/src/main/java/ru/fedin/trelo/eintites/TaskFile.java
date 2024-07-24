package ru.fedin.trelo.eintites;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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

    @Column(name = "filename", nullable = false, length = Integer.MAX_VALUE)
    private String fileName;
}

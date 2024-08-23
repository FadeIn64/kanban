package ru.fedin.trelorefactor.services;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.fedin.trelorefactor.dtos.minio.FileDto;
import ru.fedin.trelorefactor.eintites.File;
import ru.fedin.trelorefactor.exceptions.ModifyDataException;
import ru.fedin.trelorefactor.filenaming.FileName;
import ru.fedin.trelorefactor.mappers.entities.FileMapper;
import ru.fedin.trelorefactor.repositories.jpa.FileRepository;
import ru.fedin.trelorefactor.repositories.jpa.TaskRepository;
import ru.fedin.trelorefactor.services.minio.MinioService;

import java.io.InputStream;

@Service
@RequiredArgsConstructor
public class FileService {

    private final MinioService minioService;
    private final FileRepository fileRepository;
    private final TaskRepository taskRepository;
    private final FileMapper fileMapper;
    private final FileName naming;

    @Transactional
    public FileDto upload(FileDto fileDto, long taskId) {
        if (!taskRepository.existsById(taskId)) {
            throw new ModifyDataException("Task not found");
        }
        fileDto = minioService.uploadFile(fileDto);
        File file = fileMapper.toEntity(fileDto);
        file.setTaskId(taskId);
        return fileMapper.toDto(fileRepository.save(file));
    }

    public InputStream getObject(String filename,long taskId) {
        if (!filename.startsWith(taskId + "_")) {
            throw new ModifyDataException("Invalid filename format");
        }
        return minioService.getObject(filename);
    }

}

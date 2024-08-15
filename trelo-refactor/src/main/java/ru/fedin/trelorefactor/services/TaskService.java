package ru.fedin.trelorefactor.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.fedin.trelorefactor.mappers.HistoryMapper;
import ru.fedin.trelorefactor.mappers.TaskMapper;
import ru.fedin.trelorefactor.repositories.jpa.HistoryRepository;
import ru.fedin.trelorefactor.repositories.jpa.TaskRepository;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;

    private final HistoryRepository historyRepository;
    private final HistoryMapper historyMapper;

}

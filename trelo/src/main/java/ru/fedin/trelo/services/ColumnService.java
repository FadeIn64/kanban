package ru.fedin.trelo.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.fedin.trelo.dtos.DeskColumnDTO;
import ru.fedin.trelo.eintites.Desk;
import ru.fedin.trelo.eintites.DeskColumn;
import ru.fedin.trelo.mappers.ColumnMapper;
import ru.fedin.trelo.repositories.jpa.DeskColumnRepository;
import ru.fedin.trelo.repositories.jpa.DeskTaskRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class ColumnService {

    private final DeskColumnRepository columnRepository;
    private final ColumnMapper columnMapper;
    private final DeskTaskRepository taskRepository;

    private static final DeskColumn EMPTY = DeskColumn.builder().id(0).build();

    @Transactional
    public DeskColumnDTO findById(Integer id){
        var opt = columnRepository.findById(id);
        var column = opt.orElse(EMPTY);
        return columnMapper.toDto(column);
    }

    @Transactional
    public DeskColumnDTO create(DeskColumnDTO dto){
        var entity = columnMapper.toEntity(dto);
        var opt_prev = columnRepository.findByNextAndDesk(null, Desk.builder().id(dto.getDesk()).build());
        var prev = opt_prev.orElse(new DeskColumn());
        entity.setPrev(prev.getId());
        entity = columnRepository.save(entity);

        if (prev.getId() != null){
            prev.setNext(entity.getId());
            columnRepository.save(prev);
        }

        return columnMapper.toDto(entity);
    }

    @Transactional
    public DeskColumnDTO rename(Integer id, String newName){
        var opt = columnRepository.findById(id);
        if (opt.isEmpty())
            return columnMapper.toDto(EMPTY);

        var column = opt.get();
        column.setName(newName);
        column = columnRepository.save(column);
        return columnMapper.toDto(column);
    }

    @Transactional
    public List<DeskColumnDTO> move(Integer id, int offset){
        var opt = columnRepository.findById(id);
        if (opt.isEmpty())
            return new ArrayList<>();
        var column = opt.get();
        List<DeskColumn> columns = columnRepository.findAllByDesk(column.getDesk());


        for (int i = 0; i < Math.abs(offset); i++) {
            if ((offset > 0)) {
                moveRight(columns, column);
            } else {
                moveLeft(columns, column);
            }
        }

        columns = columnRepository.saveAll(columns);

        return columnMapper.toDto(columns);

    }

    private List<DeskColumn> moveRight(List<DeskColumn> columns, DeskColumn column){

        var opt_next = columns.stream()
                .filter(c -> Objects.equals(c.getId(), column.getNext())).findFirst();
        if (opt_next.isEmpty())
            return columns;
        var next = opt_next.get();

        next.setPrev(column.getPrev());
        column.setNext(next.getNext());
        column.setPrev(next.getId());
        next.setNext(column.getId());

        return columns;

    }

    public boolean removeColumn(Integer id){
        var opt = columnRepository.findById(id);
        if (opt.isEmpty())
            return true;

        var entity = opt.get();

        opt = columnRepository.findByNextAndDesk(entity.getId(), entity.getDesk());
        var prev = opt.orElse(DeskColumn.builder().build());

        opt = columnRepository.findByPrevAndDesk(entity.getId(), entity.getDesk());
        var next = opt.orElse(DeskColumn.builder().build());

        prev.setNext(next.getId());
        next.setPrev(prev.getId());

        var columns = Stream.of(prev, next).filter(c->c.getId()!=null).toList();
        columnRepository.saveAll(columns);

        var tasks = taskRepository.findAllByColumn(id);
        taskRepository.deleteAll(tasks);

        try {
            columnRepository.delete(entity);
        }catch (Exception e){
            return false;
        }

        return true;
    }

    private List<DeskColumn> moveLeft(List<DeskColumn> columns, DeskColumn column){

        var opt_prev = columns.stream()
                .filter(c -> Objects.equals(c.getId(), column.getPrev())).findFirst();
        if (opt_prev.isEmpty())
            return columns;
        var prev = opt_prev.get();

        prev.setNext(column.getNext());
        column.setPrev(prev.getPrev());
        column.setNext(prev.getId());
        prev.setPrev(column.getId());

        return columns;
    }
}

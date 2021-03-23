package com.aubay.todoaubay.mapper;

import com.aubay.todoaubay.dto.CreateInputDto;
import com.aubay.todoaubay.model.Status;
import com.aubay.todoaubay.model.Todo;
import org.mapstruct.Mapper;

import java.time.LocalDateTime;

@Mapper
public interface InputMapper {

    default Todo convert(final CreateInputDto dto) {
        final Todo todo = new Todo();
        todo.setTitle(dto.getTitle());
        todo.setText(dto.getText());
        todo.setStatus(Status.CREATED);
        todo.setCreatedAt(LocalDateTime.now());
        return todo;
    }
}

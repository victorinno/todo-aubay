package com.aubay.todoaubay.mapper;

import com.aubay.todoaubay.dto.TodoDto;
import com.aubay.todoaubay.model.Todo;
import org.mapstruct.*;

import java.util.List;

@Mapper
public interface TodoMapper {
    Todo toEntity(TodoDto dto);

    TodoDto toDto(Todo entity);

    List<Todo> toEntity(List<TodoDto> dtoList);

    List<TodoDto> toDto(List<Todo> entityList);

    @Named("partialUpdate")
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void partialUpdate(@MappingTarget Todo entity, TodoDto dto);
}

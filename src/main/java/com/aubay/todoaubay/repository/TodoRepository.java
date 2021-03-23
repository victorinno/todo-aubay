package com.aubay.todoaubay.repository;

import com.aubay.todoaubay.model.Todo;
import org.springframework.data.repository.CrudRepository;

public interface TodoRepository extends CrudRepository<Todo, Long> {
}

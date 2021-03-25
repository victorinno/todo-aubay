package com.aubay.todoaubay.endpoint;

import com.aubay.todoaubay.dto.*;
import com.aubay.todoaubay.service.TodoService;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Api
@RestController
@RequestMapping(path = "/todo")
public class TodoEndPoint {

    @Autowired
    private TodoService service;


    @GetMapping
    public List<TodoDto> findAll() {
        return this.service.findAll();
    }

    @GetMapping(path = "/{id}/statistic")
    public StatisticsTodo getStatistic(@PathVariable final Long id) {
        return this.service.getStatistic(id);
    }

    @GetMapping(path = "/{id}/start")
    public LocalDate getStart(@PathVariable final Long id) {
        return this.service.findById(id).getStart();
    }

    @PostMapping(path = "/create")
    public TodoDto create(@RequestBody final CreateInputDto createInputDto) {
        return this.service.create(createInputDto);
    }

    @PostMapping(path = "/start")
    public TodoDto start(@RequestBody final StartTodoInputDto startTodoInputDto) {
        return this.service.start(startTodoInputDto);
    }

    @PostMapping(path = "/finish")
    public TodoDto finish(@RequestBody final FinishInput finishInput) {
        return this.service.finish(finishInput.getId());
    }

    @PostMapping(path = "/format/start")
    public String formatStartDate(final FormatterInput formatterInput) {
        return this.service.formatStartDate(formatterInput);
    }

    @PostMapping(path = "/format/created-at")
    public String formatCreatedAtDate(final FormatterInput formatterInput) {
        return this.service.formatCreatedAtDate(formatterInput);
    }

    @DeleteMapping(path = "/{id}")
    public void deleteById(@PathVariable final Long id) {
        this.service.deleteById(id);
    }
}

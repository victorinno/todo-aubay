package com.aubay.todoaubay.service;

import com.aubay.todoaubay.dto.*;
import com.aubay.todoaubay.mapper.InputMapper;
import com.aubay.todoaubay.mapper.TodoMapper;
import com.aubay.todoaubay.model.Status;
import com.aubay.todoaubay.model.Todo;
import com.aubay.todoaubay.repository.TodoRepository;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@Transactional
public class TodoService {

    @Autowired
    private TodoRepository repository;

    @Autowired
    private FormatterService formatterService;

    public TodoService() {
    }

    private final TodoMapper toDoMapper = Mappers.getMapper(TodoMapper.class);
    private final InputMapper inputMapper = Mappers.getMapper(InputMapper.class);

    public TodoDto save(final Todo s) {
        return this.toDoMapper.toDto(this.repository.save(s));
    }

    private TodoDto findById(final Long aLong) {
        return this.toDoMapper.toDto(this.repository.findById(aLong).orElse(null));
    }

    public List<TodoDto> findAll() {
        return this.toDoMapper.toDto(StreamSupport.stream(this.repository.findAll().spliterator(), false)
                .collect(Collectors.toList()));
    }

    public void deleteById(final Long aLong) {
        this.repository.deleteById(aLong);
    }

    public TodoDto create(final CreateInputDto createInputDto) {
        final Todo todo = this.inputMapper.convert(createInputDto);
        return this.toDoMapper.toDto(this.repository.save(todo));
    }

    public TodoDto start(final StartTodoInputDto startTodoInputDto) {
        final Optional<Todo> todoOptional = this.repository.findById(startTodoInputDto.getId());
        return todoOptional.map(todo -> {
            todo.setStatus(Status.STARTED);
            final LocalDate actualDate = LocalDate.now();
            todo.setStart(actualDate);
            if (startTodoInputDto.getDurationInDays() > 0) {
                todo.setEnd(actualDate.plus(startTodoInputDto.getDurationInDays(), ChronoUnit.DAYS));
            }
            return this.toDoMapper.toDto(this.repository.save(todo));
        }).orElseThrow(() -> new RuntimeException("Could not find the target Todo."));
    }

    public String formatStartDate(final FormatterInput formatterInput) {
        final Optional<Todo> todoOptional = this.repository.findById(formatterInput.getId());
        return todoOptional.map(todo -> {
            final LocalDate start = todo.getStart();
            return start.format(FormatterService.getFormatter(formatterInput.getFormat()));
        }).orElseThrow(() -> new RuntimeException("Could not find the target Todo."));
    }

    public String formatCreatedAtDate(final FormatterInput formatterInput) {
        final Optional<Todo> todoOptional = this.repository.findById(formatterInput.getId());
        return todoOptional.map(todo -> {
            final LocalDateTime start = todo.getCreatedAt();
            return start.format(FormatterService.getFormatter(formatterInput.getFormat()));
        }).orElseThrow(() -> new RuntimeException("Could not find the target Todo."));
    }

    public TodoDto finish(final Long id) {
        final Optional<Todo> todoOptional = this.repository.findById(id);
        return todoOptional.map(todo -> {
            todo.setStatus(Status.FINISHED);
            todo.setFinishedAt(LocalDateTime.now());
            return this.repository.save(todo);
        }).map(this.toDoMapper::toDto).orElse(null);
    }

    public StatisticsTodo getStatistic(final Long id) {
        final TodoDto todoDto = this.findById(id);

        final Optional<LocalDate> end = Optional.ofNullable(todoDto.getEnd());
        final Optional<LocalDateTime> finishedAt = Optional.ofNullable(todoDto.getFinishedAt());
        final LocalDateTime finished = finishedAt.orElse(LocalDateTime.now());

        final int days = Period.between(todoDto.getStart(), end.orElse(LocalDate.now())).getDays();
        final long seconds = Duration.between(todoDto.getCreatedAt(), finished).getSeconds();
        final long totalHours = ChronoUnit.HOURS.between(todoDto.getCreatedAt(), finished);
        final long totalSeconds = ChronoUnit.SECONDS.between(todoDto.getCreatedAt(), finished);
        final long earlyInDays = end.map(e -> ChronoUnit.DAYS.between(finishedAt.map(LocalDateTime::toLocalDate).orElse(LocalDate.MAX), e)).orElse(0L);
        final Function<LocalDateTime, Boolean> isBeforeExample = f -> f.toLocalDate().isBefore(end.orElse(LocalDate.MAX));

        final StatisticsTodo statisticsTodo = new StatisticsTodo();
        statisticsTodo.setDuration(days);
        if (finishedAt.map(isBeforeExample).orElse(false)) {
            statisticsTodo.setEarlyInDays(earlyInDays);
        }
        statisticsTodo.setTotalHours(totalHours);
        statisticsTodo.setTotalSeconds(totalSeconds);
        statisticsTodo.setTotalDuration(convertSecToDay(seconds));
        return statisticsTodo;
    }

    private static String convertSecToDay(final Long seconds) {
        Long localSeconds = seconds;
        final long day = localSeconds / (24 * 3600);
        localSeconds = localSeconds % (24 * 3600);
        final long hour = localSeconds / 3600;
        localSeconds %= 3600;
        final long minutes = localSeconds / 60;
        localSeconds %= 60;
        return String.format("%d days %d hours %d minutes %d seconds", day, hour, minutes, localSeconds);
    }
}

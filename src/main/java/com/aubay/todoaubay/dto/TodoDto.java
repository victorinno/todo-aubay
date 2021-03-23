package com.aubay.todoaubay.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

public class TodoDto {

    private Long id;

    private String title;

    private String text;

    private LocalDate start;

    private LocalDate end;

    private LocalDateTime createdAt;

    private LocalDateTime finishedAt;

    public Long getId() {
        return this.id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(final String title) {
        this.title = title;
    }

    public String getText() {
        return this.text;
    }

    public void setText(final String text) {
        this.text = text;
    }

    public LocalDate getStart() {
        return this.start;
    }

    public void setStart(final LocalDate start) {
        this.start = start;
    }

    public LocalDate getEnd() {
        return this.end;
    }

    public void setEnd(final LocalDate end) {
        this.end = end;
    }

    public LocalDateTime getCreatedAt() {
        return this.createdAt;
    }

    public void setCreatedAt(final LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getFinishedAt() {
        return this.finishedAt;
    }

    public void setFinishedAt(final LocalDateTime finishedAt) {
        this.finishedAt = finishedAt;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TodoDto)) {
            return false;
        }
        final TodoDto toDoDTO = (TodoDto) o;
        return this.title.equals(toDoDTO.title) && this.start.equals(toDoDTO.start) && this.end.equals(toDoDTO.end);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.title, this.start, this.end);
    }
}

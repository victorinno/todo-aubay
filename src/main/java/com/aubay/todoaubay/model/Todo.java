package com.aubay.todoaubay.model;

import org.springframework.lang.NonNull;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table
public class Todo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NonNull
    private String title;

    @NonNull
    @Column(name = "text", length = 3000)
    private String text;

    private LocalDate start;

    private LocalDate end;

    @NonNull
    private LocalDateTime createdAt;

    private LocalDateTime finishedAt;

    @NonNull
    private Status status;

    public Long getId() {
        return this.id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    @NonNull
    public String getTitle() {
        return this.title;
    }

    public void setTitle(@NonNull final String title) {
        this.title = title;
    }

    @NonNull
    public String getText() {
        return this.text;
    }

    public void setText(@NonNull final String text) {
        this.text = text;
    }

    @NonNull
    public LocalDate getStart() {
        return this.start;
    }

    public void setStart(@NonNull final LocalDate start) {
        this.start = start;
    }

    @NonNull
    public LocalDate getEnd() {
        return this.end;
    }

    public void setEnd(@NonNull final LocalDate end) {
        this.end = end;
    }

    @NonNull
    public LocalDateTime getCreatedAt() {
        return this.createdAt;
    }

    public void setCreatedAt(@NonNull final LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getFinishedAt() {
        return this.finishedAt;
    }

    public void setFinishedAt(final LocalDateTime finishedAt) {
        this.finishedAt = finishedAt;
    }

    @NonNull
    public Status getStatus() {
        return this.status;
    }

    public void setStatus(@NonNull final Status status) {
        this.status = status;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Todo)) {
            return false;
        }
        final Todo todo = (Todo) o;
        return this.title.equals(todo.title) && this.start.equals(todo.start) && this.end.equals(todo.end);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.title, this.start, this.end);
    }
}

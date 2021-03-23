package com.aubay.todoaubay.dto;

public class StartTodoInputDto {

    private Long id;

    private Integer durationInDays;

    public Long getId() {
        return this.id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public Integer getDurationInDays() {
        return this.durationInDays;
    }

    public void setDurationInDays(final Integer durationInDays) {
        this.durationInDays = durationInDays;
    }
}

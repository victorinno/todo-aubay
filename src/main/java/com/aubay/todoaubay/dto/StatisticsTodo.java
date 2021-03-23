package com.aubay.todoaubay.dto;

public class StatisticsTodo {

    private Integer duration;

    private Long earlyInDays;

    private String totalDuration;

    private Long totalHours;

    private Long totalSeconds;

    public StatisticsTodo() {
    }

    public Integer getDuration() {
        return this.duration;
    }

    public void setDuration(final Integer duration) {
        this.duration = duration;
    }

    public Long getEarlyInDays() {
        return this.earlyInDays;
    }

    public void setEarlyInDays(final Long earlyInDays) {
        this.earlyInDays = earlyInDays;
    }

    public String getTotalDuration() {
        return this.totalDuration;
    }

    public void setTotalDuration(final String totalDuration) {
        this.totalDuration = totalDuration;
    }

    public Long getTotalHours() {
        return this.totalHours;
    }

    public void setTotalHours(final Long totalHours) {
        this.totalHours = totalHours;
    }

    public Long getTotalSeconds() {
        return this.totalSeconds;
    }

    public void setTotalSeconds(final Long totalSeconds) {
        this.totalSeconds = totalSeconds;
    }
}

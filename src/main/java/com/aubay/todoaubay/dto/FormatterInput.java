package com.aubay.todoaubay.dto;

public class FormatterInput {
    private final String format;
    private final Long id;

    public FormatterInput(final String format, final Long id) {
        this.format = format;
        this.id = id;
    }

    public String getFormat() {
        return this.format;
    }

    public Long getId() {
        return this.id;
    }
}

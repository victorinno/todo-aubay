package com.aubay.todoaubay.dto;

public class CreateInputDto {

    private String title;

    private String text;


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


}

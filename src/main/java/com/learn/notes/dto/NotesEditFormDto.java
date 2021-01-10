package com.learn.notes.dto;

import lombok.Data;

@Data
public class NotesEditFormDto {
    private Integer id;
    private String title;
    private String note;
}

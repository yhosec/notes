package com.learn.notes.dto;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;

@Data
public class NotesCreateFormDto {
    @NotEmpty
    @Length(max = 255)
    private String title;
    @NotEmpty
    @Length(max = 500)
    private String note;
}

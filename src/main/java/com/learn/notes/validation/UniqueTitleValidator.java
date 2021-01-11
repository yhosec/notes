package com.learn.notes.validation;

import com.learn.notes.service.NotesService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueTitleValidator implements ConstraintValidator<UniqueTitle, String> {
    @Autowired
    private NotesService notesService;

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return !notesService.findByTitle(s).isPresent();
    }
}

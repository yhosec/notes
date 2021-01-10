package com.learn.notes.controller;

import com.learn.notes.dto.NotesCreateFormDto;
import com.learn.notes.dto.NotesEditFormDto;
import com.learn.notes.entity.Notes;
import com.learn.notes.service.NotesService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Slf4j
@Controller
@RequestMapping("/notes")
public class NotesController {
    @Autowired
    private NotesService notesService;

    @GetMapping("")
    public String index(Model model) {
        model.addAttribute("notes", notesService.getAll());
        return "notes/index";
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("noteCreateForm", new NotesCreateFormDto());
        return "notes/create";
    }

    @PostMapping("/create")
    public String saveCreateForm(@Valid @ModelAttribute("noteCreateForm") NotesCreateFormDto notesCreateFormDto,
                                 BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "notes/create";
        }
        Notes notes = new Notes();
        notes.setTitle(notesCreateFormDto.getTitle());
        notes.setNote(notesCreateFormDto.getNote());
        notesService.create(notes);
        return "redirect:/notes";
    }

    @GetMapping("/detail/{id}")
    public String detail(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("note", notesService.findById(id).get());
        return "notes/detail";
   }

   @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Integer id) {
        notesService.delete(id);
       return "redirect:/notes";
   }

    @GetMapping("/edit/{id}")
    public String create(@PathVariable("id") Integer id, Model model) {
        Notes note = notesService.findById(id).get();
        NotesEditFormDto notesEditFormDto = new NotesEditFormDto();
        notesEditFormDto.setId(id);
        notesEditFormDto.setTitle(note.getTitle());
        notesEditFormDto.setNote(note.getNote());
        model.addAttribute("notesEditForm", notesEditFormDto);
        return "notes/edit";
    }

    @PostMapping("/edit/{id}")
    public String editCreateForm(@PathVariable("id") Integer id, @ModelAttribute("notesEditForm") NotesEditFormDto notesEditFormDto) {
        Notes note = notesService.findById(id).get();
        note.setTitle(notesEditFormDto.getTitle());
        note.setNote(notesEditFormDto.getNote());
        notesService.update(note);
        return "redirect:/notes";
    }
}

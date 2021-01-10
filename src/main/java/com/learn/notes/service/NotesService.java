package com.learn.notes.service;

import com.learn.notes.entity.Notes;
import com.learn.notes.repository.NotesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class NotesService {
    @Autowired
    private NotesRepository notesRepository;

    public List<Notes> getAll() {
        return notesRepository.findAll();
    }

    public Optional<Notes> findById(Integer id) {
        return notesRepository.findById(id);
    }

    public Notes create(Notes notes) {
        notes.setCreatedAt(new Date());
        notesRepository.save(notes);
        return notes;
    }

    public Notes update(Notes notes) {
        notes.setUpdatedAt(new Date());
        notesRepository.save(notes);
        return notes;
    }

    public Notes delete(Integer id) {
        Notes notes = findById(id).orElse(null);
        if (notes != null) {
            notesRepository.delete(notes);
        }
        return notes;
    }

}

package com.learn.notes.service;

import com.learn.notes.entity.Notes;
import com.learn.notes.repository.NotesRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class NotesServiceTest {
    @InjectMocks
    NotesService notesService;
    @Mock
    NotesRepository notesRepository;

    @Test
    void getAll() {
        Notes notes = dummyNotes();
        Mockito.when(notesRepository.findAll())
            .thenReturn(Arrays.asList(notes, notes));
        List<Notes> response = notesService.getAll();
        assertEquals(2, response.size());
    }

    @Test
    void findById() {
        Notes notes = dummyNotes();
        Mockito.when(notesRepository.findById(1))
            .thenReturn(Optional.of(notes));
        Optional<Notes> response = notesService.findById(1);
        assertTrue(response.isPresent());
        assertEquals(1, response.get().getId());
    }

    @Test
    void create() {
        Notes notes = dummyNotes();
        notes.setCreatedAt(null);
        Mockito.when(notesRepository.save(Mockito.any()))
            .thenReturn(notes);
        Notes response = notesService.create(notes);
        assertNotNull(response.getCreatedAt());
    }

    @Test
    void update() {
        Notes notes = dummyNotes();
        Mockito.when(notesRepository.save(Mockito.any()))
            .thenReturn(notes);
        Notes response = notesService.update(notes);
        assertNotNull(response.getUpdatedAt());
    }

    @Test
    @DisplayName("Delete correct id")
    void delete_CorrectId() {
        Notes notes = dummyNotes();
        Mockito.when(notesRepository.findById(1))
            .thenReturn(Optional.of(notes));
        Mockito.doNothing()
            .when(notesRepository)
            .delete(notes);
        Notes response = notesService.delete(1);
        assertEquals(notes, response);
    }

    @Test
    @DisplayName("Delete invalid id")
    void delete_InvalidId() {
        Notes notes = dummyNotes();
        Mockito.when(notesRepository.findById(1))
            .thenReturn(Optional.empty());
        Notes response = notesService.delete(1);
        assertNull(response);
    }

    Notes dummyNotes() {
        Notes notes = new Notes();
        notes.setId(1);
        notes.setTitle("This's title");
        notes.setNote("This's my notes");
        notes.setCreatedAt(new Date());
        return notes;
    }
}
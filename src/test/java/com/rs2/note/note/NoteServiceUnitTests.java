package com.rs2.note.note;


import com.rs2.note.note.label.Label;
import com.rs2.note.note.label.LabelService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
public class NoteServiceUnitTests {

    @Mock
    NoteRepository noteRepository;

    @Mock
    LabelService labelService;

    NoteService noteService;

    @BeforeEach
    void initBeforeEach() {

        List<Note> notes = new ArrayList<>();
        List<Label> labels = new ArrayList<>();

        Label label = new Label();
        label.setName("label 1");
        label.setId(2L);
        labels.add(label);

        Note note = new Note();
        note.setId(1L);
        note.setContent("Content 1");
        note.setNoteLabels(labels);
        notes.add(note);

        when(noteRepository.findAll()).thenReturn(notes);

        noteService = new NoteServiceImpl(noteRepository, labelService);

    }

    @DisplayName("NoteService: findAllNotes should return a 1 note")
    @Test
    void testFindAllNotes() {
        assertEquals(1, noteService.findAllNotes().size());
    }

}

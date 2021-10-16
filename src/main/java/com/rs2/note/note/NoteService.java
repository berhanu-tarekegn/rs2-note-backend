package com.rs2.note.note;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

public interface NoteService {

    /**
     * Returns a list of registered notes
     * @return
     */
    public List<Note> findAllNotes();

    /**
     * Finds notes matching the supplied filter
     * @param filter
     * @return
     */
    public List<Note> findNotes(String filter);


    /**
     * Creates a new note and returns the newly created note object
     * @param note
     * @return
     */
    public Note createNote(Note note);

    /**
     * Updates an existing note
     * @param note
     * @return
     * @throws @ItemNotFoundException
     */
    public Note updateNote(Note note);

    /**
     * Deletes an existing note
     * @param id
     * @return
     * @throws @ItemNotFoundException
     */
    public void deleteNoteById(Long id);


    /**
     * Deletes an existing note
     * @param note
     * @return
     */
    public void deleteNote(Note note);

    }

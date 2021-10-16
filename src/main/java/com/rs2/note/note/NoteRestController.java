package com.rs2.note.note;


import com.rs2.note.common.exceptions.UpdateMismatchException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/notes")
public class NoteRestController {

    private static final Logger log = LoggerFactory.getLogger(NoteRestController.class);

    private final NoteService noteService;

    NoteRestController(final NoteService noteService) {
        this.noteService = noteService;
    }

    @PostMapping
    Note createNote(@Validated @RequestBody Note note) {

        log.debug(String.format("Creating new note %s", note));

        Note persistedNote = noteService.createNote(note);

        log.debug(String.format("Created new note %s", persistedNote));

        return persistedNote;
    }

    @PutMapping(value = "/{id}")
    Note updateNote(@PathVariable("id") String id, @Validated @RequestBody Note note){

        log.debug(String.format("Updating an existing note %s using: %s", id.toString(), note.getId().toString()));

        if(null == note.getId() || !id.equals(note.getId())){
            throw new UpdateMismatchException(id, note.getId().toString());
        }

        Note updatedNote = noteService.updateNote(note);

        log.debug(String.format("Updated an existing note %s", updatedNote.toString()));

        return updatedNote;
    }

    @DeleteMapping(value = "/{id}")
    void deleteNote(@PathVariable("id") Long id) {

        log.debug(String.format("Deleting an existing note with id %s ", id.toString()));

        noteService.deleteNoteById(id);

        log.debug("Deleted a note ");
    }


}

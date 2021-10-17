package com.rs2.note.note;

import com.rs2.note.common.exceptions.ItemAlreadyExistsException;
import com.rs2.note.common.exceptions.ItemNotFoundException;
import com.rs2.note.note.label.Label;
import com.rs2.note.note.label.LabelService;
import com.rs2.note.user.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class NoteServiceImpl implements NoteService {

    private static final Logger log = LoggerFactory.getLogger(NoteServiceImpl.class);

    private final NoteRepository noteRepository;

    private final LabelService labelService;


    @Autowired
    public NoteServiceImpl(NoteRepository noteRepository, LabelService labelService) {

        this.noteRepository = noteRepository;
        this.labelService = labelService;
    }

    @Secured({User.ROLE_MANAGER})
    @Override
    public List<Note> findAllNotes() {

        log.debug("Find all notes");

        return noteRepository.findAll();
    }

    @Secured({User.ROLE_MANAGER})
    @Override
    public List<Note> findNotes(String filter) {

        log.debug(String.format("Find all notes with filter: %s", filter));

        List<Note> notes = noteRepository.search(filter);

        log.debug(String.format("Found %d notes.", null != notes ? notes.size() : 0));

        return  notes;
    }

    @Secured({User.ROLE_MANAGER})
    @Override
    public Note createNote(Note note) {

        log.debug(String.format("Adding a new note with title: %s", note.getTitle()));

        List<Label> persistedLabels = new ArrayList<>();

        if (null != note.getNoteLabels()) {

            note.getNoteLabels().stream().forEach((item) -> {

                Label persistedLabel = null;

                try {

                    persistedLabel= labelService.findLabelByName(item.getName());

                } catch (ItemNotFoundException ex) {
                    Label newLabel = new Label();
                    newLabel.setName(item.getName());
                    persistedLabel = labelService.createLabel(newLabel);
                }

                if (null != persistedLabel)
                    persistedLabels.add(persistedLabel);
            });
        }

        note.setNoteLabels(persistedLabels);

        Note persistedNote = noteRepository.save(note);

        log.debug(String.format("Created a new note with id: %d and title: %s", persistedNote.getId(), persistedNote.getTitle()));

        return persistedNote;
    }

    @Secured({User.ROLE_MANAGER})
    @Override
    public Note updateNote(Note note) {

        log.debug(String.format("Updating Note with id : %d", note.getId()));

        Note updateNote = noteRepository.save(note);

        log.debug(String.format("Updated note with id: %d and title: %s", updateNote.getId(), updateNote.getTitle()));

        return updateNote;
    }

    @Secured({User.ROLE_MANAGER})
    @Override
    public void deleteNoteById(Long id) {

        log.debug(String.format("Deleting note by id : %d", id));

        Note note = noteRepository.findById(id).orElseThrow(() -> new ItemNotFoundException(id.toString()));

        noteRepository.delete(note);

        log.debug(String.format("Note with id %d deleted", id));

    }

    @Secured({User.ROLE_MANAGER})
    @Override
    public void deleteNote(Note note) {
        log.debug(String.format("Deleting note by id : %d", note.getId()));

        noteRepository.delete(note);

        log.debug(String.format("Note with id %d deleted", note.getId()));
    }
}

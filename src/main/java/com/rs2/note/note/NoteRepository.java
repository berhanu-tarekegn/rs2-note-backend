package com.rs2.note.note;

import com.rs2.note.note.label.Label;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NoteRepository extends JpaRepository<Note, Long>, CustomNoteRepository {

    List<Note> findByNoteLabelsIn(List<Label> labels);

}

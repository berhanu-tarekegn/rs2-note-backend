package com.rs2.note.note.label;

import com.rs2.note.note.Note;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LabelRepository extends JpaRepository<Label, Long> {

    Label findByName(String name);
}

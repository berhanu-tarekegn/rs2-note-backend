package com.rs2.note.note;

import java.util.List;

public interface CustomNoteRepository {

    public List<Note> search(String filter);
}

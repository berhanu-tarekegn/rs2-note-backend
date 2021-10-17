package com.rs2.note.note.label;

import com.rs2.note.note.Note;

public interface LabelService {

    public Label findLabelByName(String name);

    public Label createLabel(Label label);

    public void deleteLabel(Label label);

}

package com.rs2.note.note;

import com.rs2.note.common.AbstractEntity;
import com.rs2.note.note.label.Label;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "note")
@Data
public class Note extends AbstractEntity implements Serializable {

    @NotNull(message = "error.validation.note.title.required")
    @Size(max = 32, message = "error.validation.note.title.invalid.length")
    @Column(name = "title")
    private String title;

    @NotNull(message = "error.validation.note.content.required")
    @Size(max = 300, message = "error.validation.note.content.invalid.length")
    @Column(name = "content")
    private String content;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "note_label",
            joinColumns = { @JoinColumn(name = "note_id", columnDefinition = "bigint") },
            inverseJoinColumns = { @JoinColumn(name = "label_id", columnDefinition = "bigint") })
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    private List<Label> noteLabels;

}

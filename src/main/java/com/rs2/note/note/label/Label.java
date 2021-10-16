package com.rs2.note.note.label;

import com.rs2.note.common.AbstractEntity;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
@Data
@Table(name = "note", indexes = {
        @Index(name = "label_idx_name", columnList = "name", unique = true)
})
public class Label extends AbstractEntity implements Serializable {

    @NotNull(message = "error.validation.label.name.required")
    @Size(max = 32, message = "error.validation.label.name.invalid.length")
    @Column(name = "name")
    private String name;

}

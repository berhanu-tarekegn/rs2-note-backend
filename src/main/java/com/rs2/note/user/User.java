package com.rs2.note.user;

import com.rs2.note.common.AbstractEntity;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
@Table(name = "user")
@Data
public class User extends AbstractEntity implements Serializable {

    @NotNull(message = "error.validation.user.name.required")
    @Size(max = 32, message = "error.validation.user.name.invalid.length")
    private String name;

    @NotNull(message = "error.validation.user.surname.required")
    @Size(max = 32, message = "error.validation.user.surname.invalid.length")
    private String surname;

}

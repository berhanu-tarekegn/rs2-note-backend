package com.rs2.note.user;

import com.rs2.note.common.AbstractEntity;
import com.rs2.note.user.credential.UserCredential;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
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

    @NotNull(message = "error.validation.user.email.required")
    @Size(min = 5, max = 255, message = "error.validation.user.email.invalid.length")
    private String email;

    @NotNull(message = "error.validation.user.credential.required")
    @OneToOne(mappedBy = "user", fetch = FetchType.LAZY, optional = true, cascade = CascadeType.ALL)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private UserCredential credential;


}

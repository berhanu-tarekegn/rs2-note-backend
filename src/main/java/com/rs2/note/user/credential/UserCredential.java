package com.rs2.note.user.credential;

import com.rs2.note.common.AbstractEntity;
import com.rs2.note.user.User;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
@Table(indexes = {
        @Index(name = "user_credential_idx_user", columnList = "user_id")
})
@Data
public class UserCredential extends AbstractEntity implements Serializable {

    private static final long serialVersionUID = -6453990222527439596L;

    //TODO: This is a basic scenario of a user has only one credential, In reality it could have many with different type. like PIN, PASSWORD, TOKEN

    @NotNull(message = "error.validation.user.credential.user.required")
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    @JoinColumn(name = "user_id", columnDefinition = "bigint")
    private User user;


    @NotNull(message = "error.validation.user.credential.credentials.required")
    @Size(min = 60, max = 60, message = "error.validation.user.credentials.credentials.invalid.length")
    private String credentials;

}

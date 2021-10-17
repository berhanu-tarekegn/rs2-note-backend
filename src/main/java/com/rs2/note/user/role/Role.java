package com.rs2.note.user.role;

import com.rs2.note.common.AbstractEntity;
import com.rs2.note.user.User;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Index;
import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name="role", indexes = {
        @Index(name = "role_idx_user_name", columnList = "user_id, name", unique = true)
})
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Role extends AbstractEntity implements GrantedAuthority, Serializable {

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false, columnDefinition = "bigint")
    @NotNull(message = "error.validation.role.user.required")
    private User user;

    @NotNull(message = "error.validation.role.name.required")
    @Size(min = 1, max = 50, message = "error.validation.role.name.invalid.length")
    private String name;

    @Override
    public String getAuthority() {

        return getName();
    }


}

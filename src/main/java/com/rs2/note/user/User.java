package com.rs2.note.user;

import com.rs2.note.common.AbstractEntity;
import com.rs2.note.user.role.Role;
import com.rs2.note.user.credential.UserCredential;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "user")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User extends AbstractEntity implements UserDetails, Serializable {

    public static final String ROLE_MANAGER = "ROLE_MANAGER";

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

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<Role> roles;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());

    }

    @Override
    public String getPassword() {
        return credential.getCredentials();
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}

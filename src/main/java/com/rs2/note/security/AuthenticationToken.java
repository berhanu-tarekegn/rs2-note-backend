package com.rs2.note.security;

import com.rs2.note.user.User;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

@Getter
@Setter
public class AuthenticationToken extends UsernamePasswordAuthenticationToken {

    private User user;

    AuthenticationToken(User user, String principal, String credentials, Collection<? extends GrantedAuthority> authorities) {

        super(principal, credentials, authorities);

        this.user = user;
    }
}

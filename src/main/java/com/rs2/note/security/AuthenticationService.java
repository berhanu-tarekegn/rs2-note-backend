package com.rs2.note.security;

import com.rs2.note.user.User;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public interface AuthenticationService {

    AuthenticationToken loginByUsernameAndPassword(String email, String credentials);

    User authenticateByUsernameAndPassword(String email, String credentials);

}

package com.rs2.note.security.error;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class InvalidCredentialsException extends AuthenticationException {

    public InvalidCredentialsException(String email) {

        super(String.format("Invalid Credentials for user %s", email));

    }

}


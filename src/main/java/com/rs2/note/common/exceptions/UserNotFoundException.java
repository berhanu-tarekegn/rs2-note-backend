package com.rs2.note.common.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.security.core.AuthenticationException;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class UserNotFoundException extends AuthenticationException {

    public UserNotFoundException(String principal) {

        super(String.format("User not found with principal: %s", principal));

    }

}

package com.rs2.note.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class AuthenticationProviderImpl implements AuthenticationProvider {

    private static final Logger log = LoggerFactory.getLogger(AuthenticationProviderImpl.class);

    private final AuthenticationService authenticationService;

    public AuthenticationProviderImpl(final AuthenticationService authenticationService){
        this.authenticationService = authenticationService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        return authenticationService.loginByUsernameAndPassword(authentication.getPrincipal().toString(), authentication.getCredentials().toString());

    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}

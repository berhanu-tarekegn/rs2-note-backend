package com.rs2.note.security;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

public class AuthenticationProviderImpl implements AuthenticationProvider {

    private static final Logger log = LoggerFactory.getLogger(AuthenticationProviderImpl.class)

    private final AuthenticationService authenticationService
    private final SystemChannelTypeEnum systemChannelType
    private final CredentialsTypeEnum credentialsType

    AuthenticationProviderImpl(SystemChannelTypeEnum systemChannelType, AuthenticationService authenticationService, CredentialsTypeEnum credentialsType) {

        this.systemChannelType = systemChannelType;
        this.authenticationService = authenticationService
        this.credentialsType = credentialsType

        assert this.credentialsType != null

    }

    @Override
    Authentication authenticate(Authentication authentication) throws AuthenticationException {

        return authenticationService.loginByEmail(systemChannelType, (String) authentication.principal, (String)authentication.credentials, credentialsType)

    }

    @Override
    boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken)
    }
}

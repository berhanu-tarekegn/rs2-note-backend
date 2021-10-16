package com.rs2.note.security;

import com.rs2.note.user.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/authenticate")
public class AuthenticationRestController {

    private static final Logger log = LoggerFactory.getLogger(AuthenticationRestController.class);

    private final AuthenticationService authenticationService;

    @Autowired
    public AuthenticationRestController(AuthenticationService authenticationService) {

        this.authenticationService = authenticationService;

    }

    @PostMapping(value = "/authenticateByUsername")
    AuthenticationResponseDTO authenticate(@Validated @RequestBody AuthenticationRequestDTO authenticationRequest) {

        if(log.isDebugEnabled())
            log.debug(String.format("Authenticating using email: %s", authenticationRequest.getPrincipal()));

        User authenticatedUser = authenticationService.authenticateByUsernameAndPassword(authenticationRequest.getPrincipal(), authenticationRequest.getCredentials());

        if(log.isDebugEnabled())
            log.debug(String.format("Authenticated using email: %s", authenticatedUser.getEmail()));

        AuthenticationResponseDTO authenticationResponse = new AuthenticationResponseDTO();
        authenticationResponse.setCredentials(authenticatedUser.getEmail());
        authenticationResponse.setPrincipal(authenticationRequest.getPrincipal());

        return authenticationResponse;
    }

}

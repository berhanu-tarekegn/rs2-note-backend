package com.rs2.note.security;

import com.rs2.note.security.error.InvalidCredentialsException;
import com.rs2.note.security.error.UserNotFoundException;
import com.rs2.note.user.User;
import com.rs2.note.user.credential.UserCredential;
import com.rs2.note.user.UserRepository;
import com.rs2.note.user.credential.UserCredentialRepository;
import com.rs2.note.user.role.Role;
import com.rs2.note.user.role.RoleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service("authenticationService")
public class AuthenticationServiceImpl implements AuthenticationService {

    private static final Logger log = LoggerFactory.getLogger(AuthenticationServiceImpl.class);

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final PasswordEncoder passwordEncoder;

    private final UserCredentialRepository userCredentialRepository;

    @Autowired
    public AuthenticationServiceImpl(final UserRepository userRepository, UserCredentialRepository userCredentialRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.userCredentialRepository = userCredentialRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public AuthenticationToken loginByUsernameAndPassword(String email, String credentials) {

        User authenticatedUser = authenticateByUsernameAndPassword(email, credentials);

        AuthenticationToken authenticationToken = new AuthenticationToken(authenticatedUser,
                email, authenticatedUser.getCredential().getCredentials(), getGrantedAuthorities(authenticatedUser));

        SecurityContextHolder.getContext().setAuthentication(authenticationToken);

        return authenticationToken;
    }

    @Override
    public User authenticateByUsernameAndPassword(String email, String credentials) {

        if(log.isDebugEnabled())
            log.debug(String.format("Authenticating %s using", email));

        User user = userRepository.findByEmailIgnoreCase(email);

        List<Role> roles = roleRepository.findRoleByUser(user);

        user.setRoles(roles);

        if(null == user) {

            if(log.isInfoEnabled())
                log.info("User not found matching email: ${email}");

            throw new UserNotFoundException(email);
        }

        UserCredential userCredential = userCredentialRepository.findByUser(user);

        if(null == userCredential) {

            if(log.isInfoEnabled())
                log.info(String.format("Credentials  not found for: %s", email));

            throw new InvalidCredentialsException(email);
        }

        boolean isAuthenticated = passwordEncoder.matches(credentials, userCredential.getCredentials());

        if(isAuthenticated) {
            return user;
        }

        else
            throw new InvalidCredentialsException(email);

    }

    private List<GrantedAuthority> getGrantedAuthorities(User user) {

        List<GrantedAuthority> grantedAuthorities = roleRepository.findRoleByUser(user).stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());

        return grantedAuthorities;

    }

    //TODO: Enable encoded password
    private String encodePassword(String password) {

        return passwordEncoder.encode(password);
    }
}

package com.rs2.note.security;

import com.rs2.note.user.User;
import com.rs2.note.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.stereotype.Component;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.InvalidParameterException;

import static com.rs2.note.security.JwtTokenUtil.*;


public class JWTTokenAuthorizationFilter extends BasicAuthenticationFilter {


    private JwtTokenUtil jwtTokenUtil;

    UserRepository userRepository;

    public JWTTokenAuthorizationFilter(AuthenticationManager authManager, JwtTokenUtil jwtTokenUtil, UserRepository userRepository) {
        super(authManager);
        this.jwtTokenUtil = jwtTokenUtil;
        this.userRepository = userRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest req,
                                    HttpServletResponse res,
                                    FilterChain chain) throws IOException, ServletException {

        String header = req.getHeader(HEADER_STRING);

        if (header == null || !header.startsWith(TOKEN_PREFIX)) {
            chain.doFilter(req, res);
            return;
        }

        UsernamePasswordAuthenticationToken authentication = getAuthentication(req);

        SecurityContextHolder.getContext().setAuthentication(authentication);
        chain.doFilter(req, res);
    }

    private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {
        String token = request.getHeader(HEADER_STRING);

        if (token == null) {
            throw new InvalidParameterException("No token found in request headers");
        }

        if (token.startsWith(TOKEN_PREFIX))
            token = token.replace(TOKEN_PREFIX, "");

        String username = jwtTokenUtil.parseTokenAndGetUsername(token);

        User user = userRepository.findByEmailIgnoreCase(username);

        if (user != null) {

            return new AuthenticationToken(user, user.getUsername(), user.getPassword(), user.getAuthorities());
        }

        return null;
    }
}

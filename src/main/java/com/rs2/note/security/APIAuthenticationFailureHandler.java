package com.rs2.note.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rs2.note.common.exceptions.ErrorCodes;
import com.rs2.note.common.exceptions.ErrorDTO;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;

public  class APIAuthenticationFailureHandler implements AuthenticationEntryPoint {


@Override
public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {

            response.setContentType("application/json");
            response.setStatus(HttpStatus.FORBIDDEN.value());

            OutputStream out = response.getOutputStream();
            ObjectMapper mapper = new ObjectMapper();
            ErrorDTO errorDTO = new ErrorDTO();
            errorDTO.setCode(ErrorCodes.ACCESS_DENIED_ERROR);
            errorDTO.setMessage(authException.getMessage());
            mapper.writeValue(out, errorDTO);
            out.flush();

    }

}

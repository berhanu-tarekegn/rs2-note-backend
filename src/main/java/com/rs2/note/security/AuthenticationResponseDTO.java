package com.rs2.note.security;

import lombok.Data;

@Data
public class AuthenticationResponseDTO {

    private String principal;

    private String credentials;
}

package com.rs2.note.security;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthenticationResponseDTO {

    private String principal;

    private String credentials;
}

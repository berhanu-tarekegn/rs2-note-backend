package com.rs2.note.security;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthenticationRequestDTO {

    private String principal;

    private String credentials;
}

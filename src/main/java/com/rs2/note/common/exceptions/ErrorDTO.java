package com.rs2.note.common.exceptions;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@RequiredArgsConstructor
public class ErrorDTO {

    private int code;

    private String message;

    private List<FieldErrorDTO> fieldErrors;

    @Override
    public String toString() {

        return String.format("Code: %d, Message: %s Field Errors: %s", code, message, fieldErrors.toString());

    }
}

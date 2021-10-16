package com.rs2.note.common.exceptions;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class FieldErrorDTO {

    private String field;

    private String message;

    @Override
    public String toString() {

        return String.format("Field: %s, Message: %s", field, message);
    }
}

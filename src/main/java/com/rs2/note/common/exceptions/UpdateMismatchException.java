package com.rs2.note.common.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.swing.*;

@ResponseStatus(HttpStatus.CONFLICT)
public class UpdateMismatchException extends AbstractException {

    public UpdateMismatchException(String newId, String oldId) {
        super(ErrorCodes.UPDATE_ID_MISMATCH, String.format("Item with new id: %s does not match with persisted item id %s", newId, oldId));
    }
}

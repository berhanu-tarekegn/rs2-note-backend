package com.rs2.note.common.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class ItemAlreadyExistsException extends AbstractException {

    public ItemAlreadyExistsException(String itemId) {
        super(ErrorCodes.ITEM_ALREADY_EXISTS, String.format("Item with id: %s already exists", itemId));
    }
}

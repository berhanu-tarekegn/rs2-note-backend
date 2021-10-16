package com.rs2.note.common.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ItemNotFoundException extends AbstractException {

    public ItemNotFoundException(String itemId) {
        super(ErrorCodes.ITEM_NOT_FOUND, String.format("Item not found with id: %s", itemId));
    }

}

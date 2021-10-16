package com.rs2.note.common.exceptions;


import lombok.Getter;
import lombok.Setter;

public abstract class AbstractException extends RuntimeException {

    @Getter
    private final int errorCode;

    public AbstractException(int errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

}

package com.rs2.note.common;

import com.rs2.note.common.exceptions.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice()
public class RestControllerAdvice extends ResponseEntityExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(RestControllerAdvice.class);

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ItemNotFoundException.class)
    @ResponseBody
    public ErrorDTO handleItemNotFoundException(ItemNotFoundException ex, WebRequest request) {

        ErrorDTO errorDTO = new ErrorDTO();

        errorDTO.setMessage(ex.getMessage());
        errorDTO.setCode(ex.getErrorCode());

        log.debug(errorDTO.toString(), ex);

        request.removeAttribute(HandlerMapping.PRODUCIBLE_MEDIA_TYPES_ATTRIBUTE, WebRequest.SCOPE_REQUEST);

        return errorDTO;

    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(UserNotFoundException.class)
    @ResponseBody
    public ErrorDTO handleUserNotFoundException(UserNotFoundException ex, WebRequest request) {

        ErrorDTO errorDTO = new ErrorDTO();

        errorDTO.setMessage(ex.getMessage());
        errorDTO.setCode(ErrorCodes.USER_NOT_FOUND_ERROR);

        log.debug(errorDTO.toString(), ex);

        request.removeAttribute(HandlerMapping.PRODUCIBLE_MEDIA_TYPES_ATTRIBUTE, WebRequest.SCOPE_REQUEST);

        return errorDTO;

    }



    @ExceptionHandler(RuntimeException.class)
    @ResponseBody
    public ResponseEntity<ErrorDTO> handleRuntimeException(RuntimeException ex, WebRequest request) {

        ErrorDTO errorDTO = new ErrorDTO();
        errorDTO.setMessage(ex.getMessage());

        if(ex instanceof AbstractException)
            errorDTO.setCode(((AbstractException)ex).getErrorCode());
        else
            errorDTO.setCode(ErrorCodes.RUNTIME_ERROR);

        log.info(errorDTO.toString(), ex);

        request.removeAttribute(HandlerMapping.PRODUCIBLE_MEDIA_TYPES_ATTRIBUTE, WebRequest.SCOPE_REQUEST);

        return new ResponseEntity<ErrorDTO>(errorDTO, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        BindingResult result = ex.getBindingResult();

        ErrorDTO errorDTO = new ErrorDTO();
        errorDTO.setCode(ErrorCodes.FIELD_VALIDATION_ERROR);
        errorDTO.setMessage("Field Error error.validation.field.error");

        errorDTO.setFieldErrors(new ArrayList<>());

        List<FieldError> fieldErrors = result.getFieldErrors();

        fieldErrors.stream().forEach((item) -> {
            FieldErrorDTO fedto = new FieldErrorDTO();
            fedto.setField(item.getField());
            fedto.setMessage(item.getDefaultMessage());
            errorDTO.getFieldErrors().add(fedto);
        });

        log.info(errorDTO.toString(), ex);

        request.removeAttribute(HandlerMapping.PRODUCIBLE_MEDIA_TYPES_ATTRIBUTE, WebRequest.SCOPE_REQUEST);

        return new ResponseEntity<>(errorDTO, HttpStatus.BAD_REQUEST);

    }


}

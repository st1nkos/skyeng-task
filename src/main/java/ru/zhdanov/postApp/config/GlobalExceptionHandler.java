package ru.zhdanov.postApp.config;

import org.modelmapper.ValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.zhdanov.postApp.exceptions.ExceptionBody;
import ru.zhdanov.postApp.exceptions.ResourceNotFoundException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ValidationException.class)
    public ExceptionBody handleIllegalState(IllegalStateException e) {
        e.printStackTrace();
        return new ExceptionBody(e.getMessage());
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ResourceNotFoundException.class)
    public ExceptionBody handlerResourceNotFound(ResourceNotFoundException e) {
        e.printStackTrace();
        return new ExceptionBody(e.getMessage());
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public ExceptionBody handleAllOtherException(Exception e) {
        e.printStackTrace();

        return new ExceptionBody(e.getMessage());
    }
}

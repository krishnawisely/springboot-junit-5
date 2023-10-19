package com.krishna.junit5testcase.exception;


import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import lombok.extern.log4j.Log4j2;

@Log4j2
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {
    
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
			MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        log.error(ex);
        List<FieldError> fieldError = ex.getBindingResult().getFieldErrors();
        List<String> errors = new ArrayList<>();
        if(fieldError != null && fieldError.size() > 0) {
            fieldError.forEach( error -> {
                errors.add(error.getDefaultMessage());
            });
        }
        return new ResponseEntity<>(errors, headers, HttpStatus.BAD_REQUEST);
    }

    /**
     * Handle Business Exception
     * @param ex
     * @param request
     * @return Conflict response
     */
    @ExceptionHandler(value = {BusinessException.class})
    public ResponseEntity<Object> handleBusinessException(Exception ex, WebRequest request){
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.CONFLICT);
    }
}

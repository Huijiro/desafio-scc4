package com.scc4.purejs.error;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.scc4.purejs.response.ErrorResponse;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(MissingProperty.class)
    public ResponseEntity<ErrorResponse> handleMissingPropertyException(MissingProperty exception) {
        return ResponseEntity.badRequest().body(new ErrorResponse(exception));
    }

    @ExceptionHandler(NotCreateId.class)
    public ResponseEntity<ErrorResponse> handleNotCreateIdException(NotCreateId exception) {
        return ResponseEntity.badRequest().body(new ErrorResponse(exception));
    }

    @ExceptionHandler(NotFound.class)
    public ResponseEntity<ErrorResponse> handleNotFoundException(NotFound exception) {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(Exception exception) {
        return ResponseEntity.internalServerError().body(new ErrorResponse(exception));
    }
}

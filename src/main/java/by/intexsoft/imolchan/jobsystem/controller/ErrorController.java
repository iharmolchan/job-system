package by.intexsoft.imolchan.jobsystem.controller;

import by.intexsoft.imolchan.jobsystem.dto.ErrorResponse;
import by.intexsoft.imolchan.jobsystem.exception.EntityNotFoundException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.validation.ConstraintViolationException;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class ErrorController {
    @ExceptionHandler(TransactionSystemException.class)
    protected ResponseEntity<ErrorResponse> handleJsonParseError(TransactionSystemException ex) {
        Throwable cause = ex.getRootCause();
        if (cause instanceof ConstraintViolationException) {
            return buildErrorResponseEntity(cause, HttpStatus.BAD_REQUEST);
        } else {
            return buildErrorResponseEntity(ex, HttpStatus.BAD_GATEWAY);
        }
    }

    @ExceptionHandler(EntityNotFoundException.class)
    protected ResponseEntity<ErrorResponse> handleEntityNotfoundException(EntityNotFoundException ex) {
        return buildErrorResponseEntity(ex, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    protected ResponseEntity<ErrorResponse> handleJsonParseError(Exception ex) {
        return buildErrorResponseEntity(ex, HttpStatus.BAD_GATEWAY);
    }

    private ResponseEntity<ErrorResponse> buildErrorResponseEntity(Throwable exception, HttpStatus httpStatus) {
        ErrorResponse errorResponse = new ErrorResponse(httpStatus);
        errorResponse.setMessage(exception.getMessage());
        errorResponse.setExtendedDescription(exception.getCause() != null ? exception.getCause().getMessage() : "");
        return new ResponseEntity<>(errorResponse, errorResponse.getStatus());
    }
}

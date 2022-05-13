package by.intexsoft.imolchan.jobsystem.controller;

import by.intexsoft.imolchan.jobsystem.dto.ErrorResponse;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class ErrorController {
    @ExceptionHandler(Exception.class)
    protected ResponseEntity<ErrorResponse> handleJsonParseError(Exception ex) {
        return buildErrorResponseEntity(ex, HttpStatus.BAD_GATEWAY);
    }

    private ResponseEntity<ErrorResponse> buildErrorResponseEntity(Exception exception, HttpStatus httpStatus) {
        ErrorResponse errorResponse = new ErrorResponse(httpStatus);
        errorResponse.setMessage(exception.getMessage());
        errorResponse.setExtendedDescription(exception.getCause() != null ? exception.getCause().getMessage() : "");
        return new ResponseEntity<>(errorResponse, errorResponse.getStatus());
    }
}

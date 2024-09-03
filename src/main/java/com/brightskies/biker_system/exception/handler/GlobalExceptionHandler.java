package com.brightskies.biker_system.exception.handler;
import com.brightskies.biker_system.exception.model.*;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.security.SignatureException;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AccountStatusException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public static ProblemDetail handleException(Exception exception) {

        ProblemDetail detail = null;

        if (exception instanceof BadCredentialsException) {
            System.err.println("BadCredentialsException, username or password is incorrect");
            detail = ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(401), exception.getMessage());
            detail.setProperty("description", "The username or password is incorrect");
            detail.setProperty("error", "BadCredentialsException");
            return detail;
        }

        if (exception instanceof AccountStatusException) {
            System.err.println("AccountStatusException, account is locked");
            detail = ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(403), exception.getMessage());
            detail.setProperty("description", "The account is locked");
        }

        if (exception instanceof AccessDeniedException) {
            detail = ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(403), exception.getMessage());
            detail.setProperty("description", "You are not authorized to access this resource");
        }

        if (exception instanceof SignatureException) {
            System.err.println("SignatureException, The JWT signature is invalid");
            detail = ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(403), exception.getMessage());
            detail.setProperty("description", "The JWT signature is invalid");
        }

        if (exception instanceof ExpiredJwtException) {
            System.err.println("ExpiredJwtException, The JWT token has expired");
            detail = ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(403), exception.getMessage());
            detail.setProperty("description", "The JWT token has expired");
        }

        if(exception instanceof CustomerNotFoundException){
            detail = ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(404), exception.getMessage());
            return detail;
        }

        if(exception instanceof BikerNotFoundException){
            detail = ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(404), exception.getMessage());
            return detail;
        }

        if(exception instanceof ProductNotFoundException){
            detail = ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(404), exception.getMessage());
            return detail;
        }

        if(exception instanceof StoreNotFoundException){
            detail = ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(404), exception.getMessage());
            return detail;
        }

        if(exception instanceof CartItemNotFoundException){
            detail = ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(404), exception.getMessage());
            return detail;
        }

        if(exception instanceof AddressNotFoundException){
            detail = ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(404), exception.getMessage());
            return detail;
        }

        if(exception instanceof OrderNotFoundException){
            detail = ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(404), exception.getMessage());
            return detail;
        }

        if(exception instanceof DeliveryAssignmentNotFoundException){
            detail = ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(404), exception.getMessage());
            return detail;
        }

        if(exception instanceof EmptyCartException){
            detail = ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(406), exception.getMessage());
            return detail;
        }

        if(exception instanceof StockQuantityExceededException){
            detail = ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(406), exception.getMessage());
            return detail;
        }

        if(exception instanceof DeliveryAssignmentException){
            detail = ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(406), exception.getMessage());
            return detail;
        }

        if(exception instanceof IllegalArgumentException){
            detail = ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(404), exception.getMessage());
            return detail;
        }

        if (detail == null) {
            System.err.println(" internal server error: " + exception.getMessage());
            detail = ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(500), exception.getMessage());
            detail.setProperty("description", "Unknown internal server error.");
        }

        return detail;
    }
}

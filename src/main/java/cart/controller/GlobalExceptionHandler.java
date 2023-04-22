package cart.controller;

import cart.exception.CartException;
import cart.exception.MemberException;
import cart.exception.ProductException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    private final Logger log = LoggerFactory.getLogger(getClass());

    @ExceptionHandler({MemberException.class, ProductException.class, CartException.class})
    public ResponseEntity<String> handleUserBadRequest(IllegalArgumentException iae) {
        return ResponseEntity.badRequest().body(iae.getMessage());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleUnknownServerException(IllegalArgumentException iae) {
        iae.printStackTrace();
        log.error(iae.getMessage());
        return ResponseEntity.internalServerError().build();
    }
}

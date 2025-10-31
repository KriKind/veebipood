package ee.kristiina.veebipood.exception;

import ee.kristiina.veebipood.model.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Date;

@ControllerAdvice
public class ControllerAdviceHandler {
    @ExceptionHandler
    public ResponseEntity<ErrorMessage> handleException(RuntimeException ex) {
        ErrorMessage errorMessage = new ErrorMessage();
        errorMessage.setMessage(ex.getMessage());
        errorMessage.setStatus(400);
        errorMessage.setTimestamp(new Date());
        return ResponseEntity.status(400).body(errorMessage);
    }
}

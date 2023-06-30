package br.com.faturamento.exceptionhandler;

import jakarta.servlet.http.HttpServletRequest;
import org.hibernate.ObjectNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@RestControllerAdvice
public class ResponseExceptionHandler extends ResponseEntityExceptionHandler {

    private static final String DEFAULT_VALIDATION_ERROR = "Validation error. Check 'errors' field for details.";
    private static final String UNKNOW_ERROR = "Unknow error occurred.";

    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
    Date HORA = Calendar.getInstance().getTime();

    @ExceptionHandler(ObjectNotFoundException.class)
    public ResponseEntity<StandardError> handleObjectNotFound(ObjectNotFoundException error, HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).
                body(new StandardError(HttpStatus.BAD_REQUEST.value(), error.getMessage(), sdf.format(HORA)));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<StandardError> handleValidation(MethodArgumentNotValidException error, HttpServletRequest request) {
        StandardError standardError = new StandardError(HttpStatus.BAD_REQUEST.value(),
                DEFAULT_VALIDATION_ERROR, sdf.format(HORA));

        error.getBindingResult().getFieldErrors().forEach(erro ->
                standardError.addError(erro.getField(), erro.getDefaultMessage()));

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(standardError);
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public ResponseEntity<StandardError> handleAllUncaughtException(Exception error, HttpServletRequest request) {
        StandardError standardError = new StandardError(HttpStatus.INTERNAL_SERVER_ERROR.value(),
                UNKNOW_ERROR, sdf.format(HORA));

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(standardError);
    }
}

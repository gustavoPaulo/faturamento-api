package br.com.billing.faturamento.exceptionhandler;

import br.com.billing.faturamento.services.exceptions.ObjectAlreadyExistsException;
import br.com.billing.faturamento.useful.Utility;
import jakarta.servlet.http.HttpServletRequest;
import org.hibernate.ObjectNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ResponseExceptionHandler {

    @ExceptionHandler(ObjectNotFoundException.class)
    private ResponseEntity<StandardError> handleObjectNotFound(ObjectNotFoundException error,
                                                               HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).
                body(new StandardError(HttpStatus.NOT_FOUND.value(),
                        error.getEntityName(), Utility.getDateTime()));
    }

    @ExceptionHandler(ObjectAlreadyExistsException.class)
    private ResponseEntity<StandardError> handleObjectAlreadyExists(ObjectAlreadyExistsException error,
                                                               HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).
                body(new StandardError(HttpStatus.BAD_REQUEST.value(),
                        Utility.ERROR_INVOICE_ALREADY_EXISTS, Utility.getDateTime()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    private ResponseEntity<StandardError> handleArgumetnNotValid(MethodArgumentNotValidException error,
                                                                 HttpServletRequest request) {
        StandardError standardError = new StandardError(HttpStatus.BAD_REQUEST.value(),
                Utility.ERROR_DEFAULT_VALIDATION, Utility.getDateTime());

        error.getBindingResult().getFieldErrors().forEach(erros ->
                standardError.addError(erros.getField(), erros.getDefaultMessage()));

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(standardError);
    }

    @ExceptionHandler(Exception.class)
    private ResponseEntity<StandardError> handleAllUncaughtException(Exception error, HttpServletRequest request) {
        StandardError standardError = new StandardError(HttpStatus.INTERNAL_SERVER_ERROR.value(),
                Utility.ERROR_UNKNOW, Utility.getDateTime());

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(standardError);
    }
}

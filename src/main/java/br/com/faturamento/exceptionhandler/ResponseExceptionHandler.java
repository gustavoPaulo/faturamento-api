package br.com.faturamento.exceptionhandler;

import br.com.faturamento.services.exceptions.ObjectAlreadyExistsException;
import br.com.faturamento.useful.Utils;
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
                        error.getEntityName(), Utils.getDateTime()));
    }

    @ExceptionHandler(ObjectAlreadyExistsException.class)
    private ResponseEntity<StandardError> handleObjectAlreadyExists(ObjectAlreadyExistsException error,
                                                               HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).
                body(new StandardError(HttpStatus.BAD_REQUEST.value(),
                        Utils.ERROR_LANCAMENTO_EXISTS, Utils.getDateTime()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    private ResponseEntity<StandardError> handleArgumetnNotValid(MethodArgumentNotValidException error,
                                                                 HttpServletRequest request) {
        StandardError standardError = new StandardError(HttpStatus.BAD_REQUEST.value(),
                Utils.ERROR_DEFAULT_VALIDATION, Utils.getDateTime());

        error.getBindingResult().getFieldErrors().forEach(erro ->
                standardError.addError(erro.getField(), erro.getDefaultMessage()));

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(standardError);
    }

    @ExceptionHandler(Exception.class)
    private ResponseEntity<StandardError> handleAllUncaughtException(Exception error, HttpServletRequest request) {
        StandardError standardError = new StandardError(HttpStatus.INTERNAL_SERVER_ERROR.value(),
                Utils.ERROR_UNKNOW, Utils.getDateTime());

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(standardError);
    }
}

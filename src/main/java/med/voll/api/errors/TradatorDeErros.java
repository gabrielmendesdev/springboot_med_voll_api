package med.voll.api.errors;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.persistence.EntityNotFoundException;
import med.voll.api.dto.error.NotValidExceptionDTO;

@RestControllerAdvice
public class TradatorDeErros {
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<String> notFoundError() {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<NotValidExceptionDTO>> badRequestError(MethodArgumentNotValidException error) {
        List<FieldError> erros = error.getFieldErrors();
        return ResponseEntity.badRequest().body(erros.stream()
                .map(NotValidExceptionDTO::new).toList());
    }
}

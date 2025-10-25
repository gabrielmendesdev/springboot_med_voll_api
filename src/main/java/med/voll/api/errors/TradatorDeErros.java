package med.voll.api.errors;

import java.util.List;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
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
        return ResponseEntity.badRequest().body(
                erros.stream().map(NotValidExceptionDTO::new).toList()
        );
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<String> handleDataIntegrityViolation(DataIntegrityViolationException ex) {
        String mensagem = extrairMensagemAmigavel(ex);
        return ResponseEntity.status(HttpStatus.CONFLICT).body(mensagem);
    }

    private String extrairMensagemAmigavel(DataIntegrityViolationException ex) {
        String msg = ex.getMostSpecificCause().getMessage();
        if (msg != null && msg.contains("Duplicate entry")) {
            return "Registro duplicado: valor já existe no banco de dados.";
        }
        return "Violação de integridade de dados.";
    }
}

package med.voll.api.dto.error;

import org.springframework.validation.FieldError;

public record NotValidExceptionDTO(String campo, String mensagem) {
    public NotValidExceptionDTO(FieldError fieldError) {
        this(fieldError.getField(), fieldError.getDefaultMessage());
    }
}

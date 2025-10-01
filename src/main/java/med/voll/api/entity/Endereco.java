package med.voll.api.entity;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Endereco {
    @NotBlank
    private String logradouro;
    @NotBlank
    @Pattern(regexp = "\\d{8}")
    private String cep;
    @NotBlank
    private String bairro;
    @NotBlank
    private String cidade;
    @NotBlank
    private String uf;
    private String numero;
    private String complemento;

    public Endereco(Endereco endereco) {
        this.logradouro = endereco.getLogradouro();
        this.cep = endereco.getCep();
        this.bairro = endereco.getBairro();
        this.cidade = endereco.getCidade();
        this.uf = endereco.getUf();
        this.numero = endereco.getNumero();
        this.complemento = endereco.getComplemento();
    }
}
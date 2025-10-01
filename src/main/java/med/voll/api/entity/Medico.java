package med.voll.api.entity;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import med.voll.api.dto.medico.DadosCadastroMedico;
import med.voll.api.enums.medico.Especialidade;

@Entity(name = "medicos")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Getter
public class Medico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Setter
    private String nome;
    @Setter
    @Email
    private String email;
    @Setter
    private String crm;
    @Enumerated(EnumType.STRING)
    @Setter
    private Especialidade especialidade;
    @Embedded
    @Setter
    private Endereco endereco;
    @Setter 
    private String telefone;

    public Medico(DadosCadastroMedico dto) {
        this.nome = dto.nome();
        this.email = dto.email();
        this.crm = dto.crm();
        this.especialidade = dto.especialidade();
        this.endereco = new Endereco(dto.endereco());
        this.telefone = dto.telefone();
    }
}

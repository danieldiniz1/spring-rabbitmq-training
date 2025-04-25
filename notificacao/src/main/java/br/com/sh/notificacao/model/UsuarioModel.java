package br.com.sh.appproposta.model;

import br.com.sh.appproposta.model.form.UsuarioForm;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Entity
@Table(name = "tb_usuario")
@Data
public class UsuarioModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private String sobrenome;

    private String cpf;

    private String telefone;

    private BigDecimal renda;

    @OneToOne(mappedBy = "usuario")
    @JoinColumn(name = "proposta_id", referencedColumnName = "id")
    @JsonBackReference
    private PropostaModel proposta;

    public static UsuarioModel valueOf(UsuarioForm form) {
        UsuarioModel model = new UsuarioModel();
        model.setNome(form.nome());
        model.setSobrenome(form.sobrenome());
        model.setCpf(form.cpf());
        model.setTelefone(form.telefone());
        model.setRenda(form.renda());
        return model;
    }
}

package br.com.sh.notificacao.model;


import br.com.sh.notificacao.model.form.UsuarioForm;
import lombok.Data;

import java.math.BigDecimal;


@Data
public class UsuarioModel {


    private Long id;

    private String nome;

    private String sobrenome;

    private String cpf;

    private String telefone;

    private BigDecimal renda;

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

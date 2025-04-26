package br.com.sh.appanalisecredito.model.form;

import java.math.BigDecimal;

public record UsuarioForm(String nome,
                          String sobrenome,
                          String cpf,
                          String telefone,
                          BigDecimal renda) {
}

package br.com.sh.appproposta.model.form;

import java.math.BigDecimal;

public record PropostaForrm(UsuarioForm usuario,
                            BigDecimal valorSolicitado,
                            Integer prazoPagamento) {
}

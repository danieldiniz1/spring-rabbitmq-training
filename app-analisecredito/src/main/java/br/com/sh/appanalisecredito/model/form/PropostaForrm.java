package br.com.sh.appanalisecredito.model.form;

import java.math.BigDecimal;

public record PropostaForrm(UsuarioForm usuario,
                            BigDecimal valorSolicitado,
                            Integer prazoPagamento) {
}

package br.com.sh.appanalisecredito.service.strategy.impl;

import br.com.sh.appanalisecredito.model.PropostaModel;
import br.com.sh.appanalisecredito.service.strategy.CalculoPonto;
import org.springframework.stereotype.Service;

@Service
public class DefaultRendaMaiorQueValorSolicitado implements CalculoPonto {
    @Override
    public Integer calcular(PropostaModel proposta) {
        return rendaMaiorQueValorSolicitado(proposta) ? 100 : 0;
    }

    private boolean rendaMaiorQueValorSolicitado(PropostaModel proposta) {
        return proposta.getUsuario().getRenda().compareTo(proposta.getValorSolicitado()) > 0;
    }
}

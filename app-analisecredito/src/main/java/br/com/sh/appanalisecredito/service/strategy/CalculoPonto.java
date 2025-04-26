package br.com.sh.appanalisecredito.service.strategy;

import br.com.sh.appanalisecredito.model.PropostaModel;

public interface CalculoPonto {

    Integer calcular(PropostaModel proposta);
}

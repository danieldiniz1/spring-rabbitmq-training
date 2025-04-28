package br.com.sh.appanalisecredito.service.strategy.impl;

import br.com.sh.appanalisecredito.model.PropostaModel;
import br.com.sh.appanalisecredito.service.strategy.CalculoPonto;
import br.com.sh.appanalisecredito.util.ValueRandom;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

import java.util.Random;

@Order(1)
@Service
public class DefaultNomeNegado implements CalculoPonto {


    @Override
    public Integer calcular(PropostaModel proposta) {
        if (nomeNegativado()) {
            throw new NegativeArraySizeException("Nome negativado para proposta: " + proposta.getId());
        }
        return 100;
    }

    private Boolean nomeNegativado() {
        return ValueRandom.randomBoolean();
    }
}

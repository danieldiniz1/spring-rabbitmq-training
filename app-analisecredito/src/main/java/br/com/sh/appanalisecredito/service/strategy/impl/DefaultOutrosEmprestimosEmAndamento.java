package br.com.sh.appanalisecredito.service.strategy.impl;

import br.com.sh.appanalisecredito.exception.EmrpestimoEmAndamentoException;
import br.com.sh.appanalisecredito.model.PropostaModel;
import br.com.sh.appanalisecredito.service.strategy.CalculoPonto;
import br.com.sh.appanalisecredito.util.ValueRandom;
import org.springframework.stereotype.Service;

@Service
public class DefaultOutrosEmprestimosEmAndamento implements CalculoPonto {

    @Override
    public Integer calcular(PropostaModel proposta) {
        if (temEmprestimosEmAndamento(proposta)) {
           throw new EmrpestimoEmAndamentoException("Proposta com empréstimos em andamento");
        }
        return calculaValorEmRisco() < 100 ? 80 : 0;
    }

    private int calculaValorEmRisco() {
        return ValueRandom.randomInteger(0,200);
    }

    private boolean temEmprestimosEmAndamento(PropostaModel proposta) {
        return ValueRandom.randomBoolean();
    }
}

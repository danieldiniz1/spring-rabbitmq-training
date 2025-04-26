package br.com.sh.appanalisecredito.service.strategy.impl;

import br.com.sh.appanalisecredito.exception.ScoreCreditoNegativoException;
import br.com.sh.appanalisecredito.model.PropostaModel;
import br.com.sh.appanalisecredito.service.strategy.CalculoPonto;
import br.com.sh.appanalisecredito.util.ValueRandom;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class DefaultPontuacaoScore implements CalculoPonto {

    @Override
    public Integer calcular(PropostaModel proposta) {
        int score = score();
        if (score < 200) {
            throw new ScoreCreditoNegativoException("Pontuação score negativa para proposta: " + proposta.getId());
        }
        return score;
    }

    private int score(){
//        simula requisição para API de pontuação de score credito
        return ValueRandom.randomInteger(0,1000);
    }
}

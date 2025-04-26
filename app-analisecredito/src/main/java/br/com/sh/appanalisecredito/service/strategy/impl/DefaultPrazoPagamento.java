package br.com.sh.appanalisecredito.service.strategy.impl;

import br.com.sh.appanalisecredito.model.PropostaModel;
import br.com.sh.appanalisecredito.service.strategy.CalculoPonto;
import org.springframework.stereotype.Service;

@Service
public class DefaultPrazoPagamento implements CalculoPonto {

    private final int VALOR_PRAZO_PAGAMENTO = 120;

    @Override
    public Integer calcular(PropostaModel proposta) {
        return pazoPagamentoMenorQueParametro(proposta.getPrazoPagamento(),VALOR_PRAZO_PAGAMENTO) ? 100 : 0;
    }

    private boolean pazoPagamentoMenorQueParametro(int prazoPagamento, int prazoMaximoDefinido) {
        return prazoPagamento < prazoMaximoDefinido;
    }
}

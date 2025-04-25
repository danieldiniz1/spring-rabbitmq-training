package br.com.sh.appproposta.service;

import br.com.sh.appproposta.model.PropostaModel;
import br.com.sh.appproposta.model.dto.PropostaDTO;

public interface NotificacaoService {

    void notificar(PropostaModel propostaDTO, String exchange);
}

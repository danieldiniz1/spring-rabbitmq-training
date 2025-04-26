package br.com.sh.appproposta.service;

import br.com.sh.appproposta.model.dto.PropostaDTO;

public interface WebSocketService {

    void notificar(PropostaDTO proposta);
}

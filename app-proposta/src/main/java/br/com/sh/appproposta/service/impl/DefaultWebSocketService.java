package br.com.sh.appproposta.service.impl;

import br.com.sh.appproposta.model.dto.PropostaDTO;
import br.com.sh.appproposta.service.WebSocketService;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class DefaultWebSocketService implements WebSocketService {

    private final SimpMessagingTemplate messagingTemplate;

    public DefaultWebSocketService(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    @Override
    public void notificar(PropostaDTO proposta) {
        messagingTemplate.convertAndSend("/propostas", proposta);

    }
}

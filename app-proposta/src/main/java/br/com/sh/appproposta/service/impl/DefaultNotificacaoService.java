package br.com.sh.appproposta.service.impl;

import br.com.sh.appproposta.model.PropostaModel;
import br.com.sh.appproposta.service.NotificacaoService;
import lombok.AllArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class DefaultNotificacaoService implements NotificacaoService {

    private final static Logger logger = LogManager.getLogger(DefaultNotificacaoService.class);
    private final RabbitTemplate rabbitTemplate;

    @Override
    public void notificar(PropostaModel proposta, String exchange) {
        rabbitTemplate.convertAndSend(exchange, "", proposta);
        logger.info("Proposta {} notificada com sucesso", proposta.getId());

    }
}

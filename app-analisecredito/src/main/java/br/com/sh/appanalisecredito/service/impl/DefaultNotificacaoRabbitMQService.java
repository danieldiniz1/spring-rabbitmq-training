package br.com.sh.appanalisecredito.service.impl;

import br.com.sh.appanalisecredito.model.PropostaModel;
import br.com.sh.appanalisecredito.service.NotificacaoRabbitMQService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
public class DefaultNotificacaoRabbitMQService implements NotificacaoRabbitMQService {

    private static final Logger LOGGER = LogManager.getLogger(DefaultNotificacaoRabbitMQService.class);
    private RabbitTemplate rabbitTemplate;

    public DefaultNotificacaoRabbitMQService(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    public void NotificarFila(PropostaModel proposta, String exchange, String queueName, String routingKey) {
        try {
            rabbitTemplate.convertAndSend(exchange, "", proposta);
        } catch (RuntimeException e) {
            LOGGER.error("Erro ao enviar proposta :{} para a fila: {}. erro: {}", proposta.getId(), queueName, e.getCause());
        }
    }
}

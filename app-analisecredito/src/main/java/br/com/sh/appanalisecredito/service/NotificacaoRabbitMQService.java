package br.com.sh.appanalisecredito.service;

import br.com.sh.appanalisecredito.model.PropostaModel;

public interface NotificacaoRabbitMQService {

    void NotificarFila(PropostaModel proposta, String exchange, String queueName, String routingKey);
}

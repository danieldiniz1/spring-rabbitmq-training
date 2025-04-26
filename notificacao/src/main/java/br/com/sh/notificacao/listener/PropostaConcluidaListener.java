package br.com.sh.notificacao.listener;

import br.com.sh.notificacao.constants.MessageConstants;
import br.com.sh.notificacao.model.PropostaModel;
import br.com.sh.notificacao.service.NotificacaoService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;



@Component
public class PropostaConcluidaListener {

    private static final Logger logger = LogManager.getLogger(PropostaConcluidaListener.class);
    private final RabbitTemplate rabbitTemplate;
    private final NotificacaoService notificacaoService;

    public PropostaConcluidaListener(RabbitTemplate rabbitTemplate, NotificacaoService notificacaoService) {
        this.rabbitTemplate = rabbitTemplate;
        this.notificacaoService = notificacaoService;
    }

    @RabbitListener(queues = "${rabbitmq.queue.proposta.concluida}")
    public void listenQueuePropostaConcluida(PropostaModel proposta) {
        logger.info("enviando notificacao para usuario da proposta id: {}!", proposta.getId());
        verificarStatusEEnviarMensagem(proposta);
    }

    private void verificarStatusEEnviarMensagem(PropostaModel proposta) {
        if (proposta.getAprovada()){
            logger.info(String.format(MessageConstants.MENSAGEM_PROPOSTA_APROVADA, proposta.getUsuario().getNome(), proposta.getId(), MessageConstants.PRAZO_LIBERACAO));
            notificacaoService.notificarPorSMS(MessageConstants.MENSAGEM_PROPOSTA_REPROVADA, proposta.getUsuario().getTelefone());
        }
        if (!proposta.getAprovada()){
            logger.info(String.format(MessageConstants.MENSAGEM_PROPOSTA_REPROVADA, proposta.getUsuario().getNome(), proposta.getId()));
            notificacaoService.notificarPorSMS(MessageConstants.MENSAGEM_PROPOSTA_REPROVADA, proposta.getUsuario().getTelefone());
        }
    }
}

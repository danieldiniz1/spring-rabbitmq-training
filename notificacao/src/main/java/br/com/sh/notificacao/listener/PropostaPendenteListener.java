package br.com.sh.notificacao.listener;

import br.com.sh.notificacao.constants.MessageConstants;
import br.com.sh.notificacao.model.PropostaModel;
import br.com.sh.notificacao.service.NotificacaoService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class PropostaPendenteListener {

    private final NotificacaoService notificacaoService;

    public PropostaPendenteListener(NotificacaoService notificacaoService) {
        this.notificacaoService = notificacaoService;
    }

    @RabbitListener(queues = "${rabbitmq.queue.proposta.pendente}")
    public void propostaPendete(PropostaModel proposta) {
        String mensagem = String.format( MessageConstants.MENSAGEM_PENDENTE, proposta.getUsuario().getNome(), proposta.getId());
        notificacaoService.notificarPorSMS(mensagem);

    }
}

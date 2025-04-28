package br.com.sh.appanalisecredito.service.impl;

import br.com.sh.appanalisecredito.constants.MessageConstants;
import br.com.sh.appanalisecredito.exception.EmprestimoEmAndamentoException;
import br.com.sh.appanalisecredito.model.PropostaModel;
import br.com.sh.appanalisecredito.service.AnaliseCreditoService;
import br.com.sh.appanalisecredito.service.NotificacaoRabbitMQService;
import br.com.sh.appanalisecredito.service.strategy.CalculoPonto;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.amqp.AmqpException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DefaultAnaliseCreditoService implements AnaliseCreditoService {

    private static final Logger LOGGER = LogManager.getLogger(DefaultAnaliseCreditoService.class);

    @Value("${rabbitmq.queue.proposta.concluida}")
    private String propostaConcluidaQueueName;

    @Value("${rabbitmq.propostaconcluida.exchange}")
    private String propostaConcluidaExtName;

    private final List<CalculoPonto> listCalculadoras;

    private final NotificacaoRabbitMQService notificacaoRabbitMQService;


    public DefaultAnaliseCreditoService(List<CalculoPonto> listCalculadoras, NotificacaoRabbitMQService notificacaoRabbitMQService) {
        this.listCalculadoras = listCalculadoras;
        this.notificacaoRabbitMQService = notificacaoRabbitMQService;
    }

    @Override
    public void analiseCredito(PropostaModel proposta) {
        try {
            int pontuacao = listCalculadoras.stream().mapToInt(impl -> impl.calcular(proposta)).sum();
            LOGGER.info("Pontuacao do proposta: {}. proposta id: {}", pontuacao, proposta.getId());
            verificaAprovacao(pontuacao, proposta);

        } catch (RuntimeException e) {
            LOGGER.error("Erro ao calcular pontuacao proposta id: {}. Erro: {}", proposta.getId(), e.getMessage());
            reprovarProposta(proposta, 0, e.getMessage());
        }
        notificacaoRabbitMQService.NotificarFila(proposta, propostaConcluidaExtName, propostaConcluidaQueueName, "");
    }

    private void verificaAprovacao(int pontuacao, PropostaModel proposta) {
        if (pontuacao > 350) {
            aprovarProposta(proposta, pontuacao);
        } else {
            reprovarProposta(proposta, pontuacao, "Pontuacao abaixo do limite de credito");
        }
    }

    private void reprovarProposta(PropostaModel proposta, int pontuacao, String mensagem) {
        proposta.setAprovada(Boolean.FALSE);
        proposta.setObservacao(mensagem);
        LOGGER.info("Proposta id: {} aprovada com pontuacao: {}", proposta.getId(), pontuacao);
    }

    private void aprovarProposta(PropostaModel proposta, int pontuacao) {
        proposta.setAprovada(Boolean.TRUE);
        proposta.setObservacao(String
                .format(MessageConstants.LIBERAR_RECURSO, proposta.getUsuario().getNome(), proposta.getId(),MessageConstants.PRAZO_LIBERACAO ));
        LOGGER.info("Proposta id: {} aprovada com pontuacao: {}", proposta.getId(), pontuacao);
    }
}

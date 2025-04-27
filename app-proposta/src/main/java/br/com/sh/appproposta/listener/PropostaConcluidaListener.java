package br.com.sh.appproposta.listener;

import br.com.sh.appproposta.exception.PropostaNotFoundException;
import br.com.sh.appproposta.model.PropostaModel;
import br.com.sh.appproposta.model.dto.PropostaDTO;
import br.com.sh.appproposta.repository.PropostaRepository;
import br.com.sh.appproposta.service.WebSocketService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
public class PropostaConcluidaListener {

    private static final Logger LOGGER = LogManager.getLogger(PropostaConcluidaListener.class);
    private final RabbitTemplate rabbitTemplate;
    private final PropostaRepository propostaRepository;
    private final WebSocketService webSocketService;

    public PropostaConcluidaListener(RabbitTemplate rabbitTemplate, PropostaRepository propostaRepository, WebSocketService webSocketService) {
        this.rabbitTemplate = rabbitTemplate;
        this.propostaRepository = propostaRepository;
        this.webSocketService = webSocketService;
    }

    @RabbitListener(queues = "${rabbitmq.propostaconcluida.queue}")
    public void consumirListenerPropostaConcluida(PropostaModel proposta) {
        LOGGER.info("Consumida do proposta {} com status {} e observacao {}.",
                proposta.getId(), proposta.getAprovada(), proposta.getObservacao());
//        PropostaModel propostaModel = propostaRepository
//                .findById(proposta.getId())
//                .orElseThrow(() -> new PropostaNotFoundException("Proposta não encontrada com id: " + proposta.getId()));
//
//        atualizarDadosDeProposta(propostaModel, proposta);
        propostaRepository.updateProposta(proposta.getId(), proposta.getAprovada(), proposta.getObservacao());
        webSocketService.notificar(PropostaDTO.valueOf(proposta));
    }

//    private void atualizarDadosDeProposta(PropostaModel propostaModel, PropostaModel proposta) {
//        propostaModel.setAprovada(proposta.getAprovada());
//        propostaModel.setObservacao(proposta.getObservacao());
//        propostaRepository.save(propostaModel);
//    }
}

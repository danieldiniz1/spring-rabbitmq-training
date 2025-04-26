package br.com.sh.appproposta.job;

import br.com.sh.appproposta.model.PropostaModel;
import br.com.sh.appproposta.repository.PropostaRepository;
import br.com.sh.appproposta.service.NotificacaoService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;


@Component
public class PropostaSemIntegracaoJob {

    private final static Logger LOG = LogManager.getLogger(PropostaSemIntegracaoJob.class);
    @Value("${rabbitmq.propostapendent.exchange}")
    private String PROPOSTA_PENDENTE_EX;
    private final PropostaRepository propostaRepository;
    private final NotificacaoService notificacaoService;

    public PropostaSemIntegracaoJob(PropostaRepository propostaRepository,
                                    NotificacaoService notificacaoService) {
        this.propostaRepository = propostaRepository;
        this.notificacaoService = notificacaoService;
    }

    @Scheduled(fixedDelay = 20,timeUnit = TimeUnit.SECONDS)
    public void executasearchForPropostaSemIntegracao() {
        propostaRepository.findAllByintegradaIsFalse().forEach(proposta -> {
            try {
                notificacaoService.notificar(proposta,PROPOSTA_PENDENTE_EX);
                atualizarProposta(proposta);
            } catch (RuntimeException e) {
                LOG.error("Erro ao notificar proposta: {} e erro {}", proposta.getId(), e);
            }
        });
    }

    private void atualizarProposta(PropostaModel proposta) {
        proposta.setIntegrada(Boolean.TRUE);
        propostaRepository.save(proposta);
    }
}

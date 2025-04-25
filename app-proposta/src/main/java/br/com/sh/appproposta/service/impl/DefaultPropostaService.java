package br.com.sh.appproposta.service.impl;

import br.com.sh.appproposta.model.PropostaModel;
import br.com.sh.appproposta.model.dto.PropostaDTO;
import br.com.sh.appproposta.model.form.PropostaForrm;
import br.com.sh.appproposta.repository.PropostaRepository;
import br.com.sh.appproposta.repository.UsuarioRepository;
import br.com.sh.appproposta.service.NotificacaoService;
import br.com.sh.appproposta.service.PropostaService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Service
public class DefaultPropostaService implements PropostaService {

    @Value("${rabbitmq.propostapendent.exchange}")
    private String PROPOSTA_PENDENTE_EX;
    private final PropostaRepository propostaRepository;
    private final NotificacaoService notificacaoService;
    private final UsuarioRepository usuarioRepository;

    public DefaultPropostaService(PropostaRepository propostaRepository,
                                  NotificacaoService notificacaoService,
                                  UsuarioRepository usuarioRepository) {
        this.propostaRepository = propostaRepository;
        this.notificacaoService = notificacaoService;
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public PropostaDTO saveProposta(PropostaForrm propostaForrm) {
//        return PropostaMapper.INSTANCE.convertModelToDto(propostaRepository.save(Objects.requireNonNull(PropostaMapper.INSTANCE.convertDtoToModel(propostaForrm))));
        PropostaModel propostaModel = propostaRepository
                .save(Objects.requireNonNull(PropostaModel.valueOf(propostaForrm)));
        notificarRabbitMQ(propostaModel, PROPOSTA_PENDENTE_EX);
        return PropostaDTO.valueOf(propostaModel);
    }

    @Override
    public PropostaDTO findById(Long id) {
        return propostaRepository.findById(id).map(PropostaDTO::valueOf).orElse(null);
    }

    @Override
    public List<PropostaDTO> findAll() {
        List<PropostaDTO> list = propostaRepository.findAll().stream().map(PropostaDTO::valueOf).toList();
        return list.isEmpty() ? Collections.emptyList() : list;
    }

    private void notificarRabbitMQ(PropostaModel proposta, String exchange) {
        try {
            notificacaoService.notificar(proposta,PROPOSTA_PENDENTE_EX);
        } catch (RuntimeException e) {
            proposta.setIntegrada(Boolean.FALSE);
            propostaRepository.save(proposta);
        }
    }
}

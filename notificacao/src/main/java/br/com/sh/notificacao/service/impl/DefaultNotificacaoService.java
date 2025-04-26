package br.com.sh.notificacao.service.impl;

import br.com.sh.notificacao.service.NotificacaoService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

@Service
public class DefaultNotificacaoService implements NotificacaoService {

    private static final Logger LOGGER = LogManager.getLogger(DefaultNotificacaoService.class);

    @Override
    public void notificarPorSMS(String mensagem, String telefone) {
        LOGGER.info("Notificação para telefone {} por SMS: {}",telefone, mensagem);
    }

    @Override
    public void notificarPorEmail(String mensagem, String email) {
        //TODO implementar envio de email com spring mail
    }
}

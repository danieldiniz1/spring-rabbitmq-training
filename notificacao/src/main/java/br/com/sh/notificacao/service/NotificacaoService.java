package br.com.sh.notificacao.service;

public interface NotificacaoService {

    void notificarPorSMS(String mensagem);
    void notificarPorEmail(String mensagem);
}

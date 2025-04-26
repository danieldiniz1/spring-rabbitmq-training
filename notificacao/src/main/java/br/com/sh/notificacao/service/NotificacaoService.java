package br.com.sh.notificacao.service;

public interface NotificacaoService {

    void notificarPorSMS(String mensagem, String telefone);
    void notificarPorEmail(String mensagem, String email);
}

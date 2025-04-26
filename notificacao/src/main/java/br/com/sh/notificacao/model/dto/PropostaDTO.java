package br.com.sh.notificacao.model.dto;


import br.com.sh.notificacao.model.PropostaModel;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class PropostaDTO {

    private String id, nome, sobrenome, telefone, cpf,observacao;
    private BigDecimal renda, valorSolicitado;
    private Boolean aprovado;

    public static PropostaDTO valueOf(PropostaModel model) {
        PropostaDTO propostaDTO = new PropostaDTO();
        propostaDTO.setId(model.getId().toString());
        propostaDTO.setNome(model.getUsuario().getNome());
        propostaDTO.setSobrenome(model.getUsuario().getSobrenome());
        propostaDTO.setTelefone(model.getUsuario().getTelefone());
        propostaDTO.setCpf(model.getUsuario().getCpf());
        propostaDTO.setRenda(model.getUsuario().getRenda());
        propostaDTO.setValorSolicitado(model.getValorSolicitado());
        propostaDTO.setObservacao(model.getObservacao());
        propostaDTO.setAprovado(model.getAprovada());
        return propostaDTO;
    }
}

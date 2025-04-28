package br.com.sh.appanalisecredito.model;



import br.com.sh.appanalisecredito.model.form.PropostaForrm;
import lombok.Data;

import java.math.BigDecimal;


@Data
public class PropostaModel {

    private Long id;

    private BigDecimal valorSolicitado;

    private int prazoPagamento;

    private Boolean aprovada;


    private Boolean integrada;

    private String observacao;

    private UsuarioModel usuario;

    public static PropostaModel valueOf(PropostaForrm form) {
        PropostaModel model = new PropostaModel();
        model.setIntegrada(Boolean.TRUE);
        model.setValorSolicitado(form.valorSolicitado());
        model.setPrazoPagamento(form.prazoPagamento());
        model.setUsuario(UsuarioModel.valueOf(form.usuario()));
        return model;
    }
}

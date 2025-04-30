package br.com.sh.appproposta.model;

import br.com.sh.appproposta.model.form.PropostaForrm;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Entity
@Table(name = "tb_proposta")
@Data
public class PropostaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "valor_solicitado")
    private BigDecimal valorSolicitado;

    @Column(name = "prazo_pagamento")
    private int prazoPagamento;

    private Boolean aprovada;

    @Column(columnDefinition = "boolean default true")
    private Boolean integrada;

    private String observacao;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "usuario_id", referencedColumnName = "id")
    @JsonManagedReference
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

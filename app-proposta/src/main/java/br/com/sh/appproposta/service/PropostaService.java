package br.com.sh.appproposta.service;

import br.com.sh.appproposta.model.dto.PropostaDTO;
import br.com.sh.appproposta.model.form.PropostaForrm;

import java.util.List;

public interface PropostaService {

    PropostaDTO saveProposta(PropostaForrm propostaForrm);

    PropostaDTO findById(Long id);

    List<PropostaDTO> findAll();
}

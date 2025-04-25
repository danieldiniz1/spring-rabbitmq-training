package br.com.sh.appproposta.repository;

import br.com.sh.appproposta.model.PropostaModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PropostaRepository extends JpaRepository<PropostaModel, Long> {

    List<PropostaModel> findAllByintegradaIsFalse();
}

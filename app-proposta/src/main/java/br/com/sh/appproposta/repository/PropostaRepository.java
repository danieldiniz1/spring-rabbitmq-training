package br.com.sh.appproposta.repository;

import br.com.sh.appproposta.model.PropostaModel;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PropostaRepository extends JpaRepository<PropostaModel, Long> {

    List<PropostaModel> findAllByintegradaIsFalse();

    @Transactional
    @Modifying
//    @Query(value = "UPDATE tb_proposta SET aprovada = :aprovada, observacao = :observacao WHERE id = :id", nativeQuery = true)
    @Query(value = "UPDATE apiproposta.tb_proposta SET aprovada = :aprovada, observacao = :observacao WHERE id = :id", nativeQuery = true)
    void updateProposta(Long id, Boolean aprovada, String observacao);
}

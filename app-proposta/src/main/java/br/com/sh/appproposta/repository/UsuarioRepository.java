package br.com.sh.appproposta.repository;

import br.com.sh.appproposta.model.UsuarioModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioModel,Long> {

    boolean existsByCpf(String cpf);
    UsuarioModel findByCpf(String cpf);
}

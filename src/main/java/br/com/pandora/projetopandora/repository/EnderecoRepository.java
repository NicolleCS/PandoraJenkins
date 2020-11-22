package br.com.pandora.projetopandora.repository;

import br.com.pandora.projetopandora.data.model.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Integer> {

    List<Endereco> findAllByFkSolicitante_Id(Integer id);

}

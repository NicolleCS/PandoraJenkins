package br.com.pandora.projetopandora.repository;

import br.com.pandora.projetopandora.data.model.CategoriaServico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoriaServicoRepository extends JpaRepository<CategoriaServico, Integer> {

    List<CategoriaServico> findAll();

    Optional<CategoriaServico> getByIdCategoria(Integer id);


}

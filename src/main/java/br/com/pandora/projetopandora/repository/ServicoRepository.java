package br.com.pandora.projetopandora.repository;

import br.com.pandora.projetopandora.data.model.Servico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface ServicoRepository extends JpaRepository<Servico, Integer> {

    List<Servico> findAll();

    List findAllByFkCategoriaServicoIdCategoria(Integer id);

    @Query("select s from Servico s where s.titulo like %?1% or s.descricao like %?1%")
    List findAllByPalavra(String filtro);


    @Transactional
    @Modifying
    void deleteByIdServico(Integer id);

    List findAllByFkPrestador_Id(Integer id);

    List findAllByIdServico(Integer id);

}

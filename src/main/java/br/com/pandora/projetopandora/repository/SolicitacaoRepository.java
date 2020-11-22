package br.com.pandora.projetopandora.repository;

import br.com.pandora.projetopandora.data.model.Prestador;
import br.com.pandora.projetopandora.data.model.Solicitacao;
import br.com.pandora.projetopandora.data.model.Solicitante;
import br.com.pandora.projetopandora.data.model.StatusSolicitacaoEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SolicitacaoRepository extends JpaRepository<Solicitacao, Integer> {

    List<Solicitacao> findByStatusAndFkPrestador(StatusSolicitacaoEnum status, Prestador id);

    List<Solicitacao> findByFkPrestador(Prestador id);

    List<Solicitacao> findAllByFkPrestador_Id(Integer id);

    List<Solicitacao> findByStatusAndFkSolicitante(StatusSolicitacaoEnum status, Solicitante id);

    List<Solicitacao> findByFkSolicitante(Solicitante id);

    Optional<List<Solicitacao>> findAllByFkPrestador(Prestador id);

}

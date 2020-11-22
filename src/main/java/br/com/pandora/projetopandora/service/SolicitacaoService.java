package br.com.pandora.projetopandora.service;

import br.com.pandora.projetopandora.exception.RegraNegocioException;
import br.com.pandora.projetopandora.data.model.*;
import br.com.pandora.projetopandora.repository.PrestadorRepository;
import br.com.pandora.projetopandora.repository.SolicitacaoRepository;
import br.com.pandora.projetopandora.repository.SolicitanteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class SolicitacaoService {

    @Autowired
    private SolicitanteRepository solicitanteRepository;

    @Autowired
    private PrestadorRepository prestadorRepository;

    @Autowired
    private SolicitacaoRepository solicitacaoRepository;

    public Solicitacao criarSolicitacao(Solicitacao pedido, Integer idPrestador, Integer idSolicitante) {
        Optional<Prestador> prestador = prestadorRepository.findById(idPrestador);
        Optional<Solicitante> solicitante = solicitanteRepository.findById(idSolicitante);
        if (prestador.isPresent()) {
            pedido.setFkPrestador(prestador.get());
        } else {
            throw new RegraNegocioException("Prestador inválido");
        }
        if (solicitante.isPresent()) {
            pedido.setFkSolicitante(solicitante.get());
        } else {
            throw new RegraNegocioException("Solicitante inválido");
        }
        pedido.setStatus(StatusSolicitacaoEnum.SOLICITADO);
        return solicitacaoRepository.save(pedido);
    }

    public Solicitacao alterarStatus(Integer idSolicitacao, StatusSolicitacaoEnum status) throws Exception {
        try {
            Optional<Solicitacao> solicitacao = solicitacaoRepository.findById(idSolicitacao);
            if (solicitacao.get().getStatus().equals(status)) {
                throw new RegraNegocioException(String.format("Essa solicitação já possuí o status %s", status));
            }
            solicitacao.get().setStatus(status);
            return solicitacaoRepository.save(solicitacao.get());
        } catch (RegraNegocioException e) {
            throw new RegraNegocioException("Solicitação não encontrada");
        } catch (Exception ex) {
            throw new Exception(ex);
        }

    }

    public List<Solicitacao> buscarPorStatusEPrestador(String status, Integer id) {
        try {
            return solicitacaoRepository.findByStatusAndFkPrestador(StatusSolicitacaoEnum.valueOf(status.toUpperCase()), prestadorRepository.findById(id).get());
        } catch (RegraNegocioException e) {
            throw new RegraNegocioException(String.format("Nenhuma solicitação com status %s ou usuário inexistente", status));
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException("Usuario não encontrado");
        }
    }

    public List<Solicitacao> buscarPorPrestador(Integer id) {
        try {
            return solicitacaoRepository.findByFkPrestador(prestadorRepository.findById(id).get());
        } catch (RegraNegocioException e) {
            throw new RegraNegocioException("Usuário inexistente");
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException("Usuario não encontrado");
        }
    }

    public List<Solicitacao> buscarPorStatusESolicitante(String status, Integer id) {
        try {
            return solicitacaoRepository.findByStatusAndFkSolicitante(StatusSolicitacaoEnum.valueOf(status.toUpperCase()), solicitanteRepository.findById(id).get());
        } catch (RegraNegocioException e) {
            throw new RegraNegocioException(String.format("Nenhuma solicitação com status %s ou usuário inexistente", status));
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException("Usuario não encontrado");
        }
    }

    public List<Solicitacao> buscarPorSolicitante(Integer id) {
        try {
            return solicitacaoRepository.findByFkSolicitante(solicitanteRepository.findById(id).get());
        } catch (RegraNegocioException e) {
            throw new RegraNegocioException("Usuário inexistente");
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException("Usuario não encontrado");
        }
    }

    public Boolean addAvaliacao(Avaliacao avaliacao, Integer idSolicitacao) {
        Optional<Solicitacao> solicitacao = solicitacaoRepository.findById(idSolicitacao);
        if (solicitacao.isPresent()) {
            solicitacao.get().setFkAvaliacao(avaliacao);
            solicitacaoRepository.save(solicitacao.get());
            return true;
        }
        return false;
    }
}

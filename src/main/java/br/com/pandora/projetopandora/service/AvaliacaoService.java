package br.com.pandora.projetopandora.service;

import br.com.pandora.projetopandora.data.dto.AvaliacaoDto;
import br.com.pandora.projetopandora.exception.RegraNegocioException;
import br.com.pandora.projetopandora.data.model.Avaliacao;
import br.com.pandora.projetopandora.data.model.Solicitacao;
import br.com.pandora.projetopandora.repository.AvaliacaoRepository;
import br.com.pandora.projetopandora.repository.PrestadorRepository;
import br.com.pandora.projetopandora.repository.SolicitacaoRepository;
import br.com.pandora.projetopandora.data.vo.AvaliacaoVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class AvaliacaoService {

    @Autowired
    AvaliacaoRepository avaliacaoRepository;

    @Autowired
    SolicitacaoRepository solicitacaoRepository;

    @Autowired
    PrestadorRepository prestadorRepository;

    @Autowired
    SolicitacaoService solicitacaoService;

    public Avaliacao criarAvaliacao(AvaliacaoDto avaliacao) {
        if (avaliacao.getNota() <= 5.0 && avaliacao.getNota() >= 0.0) {
            Avaliacao novaAvaliacao = avaliacaoRepository.save(new Avaliacao(avaliacao.getNota(), avaliacao.getAvaliacao()));
            if (solicitacaoService.addAvaliacao(novaAvaliacao, avaliacao.getIdSolicitacao())) {
                return novaAvaliacao;
            }else{
                throw new RegraNegocioException("O Id da solicitação não é válido!");
            }
        } else {
            throw new RegraNegocioException("A avaliação deve ser um numero entre 0 e 5!");
        }
    }


    public List<Avaliacao> buscarTodasAvaliacoes() {
        List<Avaliacao> avaliacoes = avaliacaoRepository.findAll();
        return avaliacoes;
    }

    public Avaliacao atualizarAvaliacao(Avaliacao avaliacao) {
        avaliacaoRepository.save(avaliacao);
        return avaliacao;
    }

    public void excluirAvaliacao(Integer id) {
        avaliacaoRepository.deleteById(id);
        return;
    }

    public Double getMediaAvaliacoesPorPrestador(Integer idPrestador) {
        try {
            Optional<List<Solicitacao>> solicitacoes = solicitacaoRepository.findAllByFkPrestador(prestadorRepository.findById(idPrestador).get());
            List<Avaliacao> avaliadas = new ArrayList<>();
            for (Solicitacao solicitacao : solicitacoes.get()) {
                if (solicitacao.getFkAvaliacao() != null) {
                    avaliadas.add(solicitacao.getFkAvaliacao());
                }
            }
            Double soma = 5.0;
            for (Avaliacao avaliacao : avaliadas) {
                soma += avaliacao.getNota();
            }
            return soma / (avaliadas.size() + 1);

        } catch (NoSuchElementException e) {
            return 5.0;
        }
    }

    public List<AvaliacaoVO> getAvaliacoesPorPrestador(Integer idPrestador) {
        try {
            Optional<List<Solicitacao>> solicitacoes = solicitacaoRepository.findAllByFkPrestador(prestadorRepository.findById(idPrestador).get());
            List<Solicitacao> avaliadas = new ArrayList<>();
            for (Solicitacao solicitacao : solicitacoes.get()) {
                if (solicitacao.getFkAvaliacao() != null) {
                    avaliadas.add(solicitacao);
                }
            }
            List<AvaliacaoVO> avaliacaoVOs = new ArrayList<>();
            for (Solicitacao solicitacao : avaliadas) {
                avaliacaoVOs.add(new AvaliacaoVO(
                        solicitacao.getFkSolicitante().getId(),
                        solicitacao.getFkSolicitante().getNome(),
                        solicitacao.getFkSolicitante().getImagem(),
                        solicitacao.getFkAvaliacao().getNota(),
                        solicitacao.getFkAvaliacao().getAvaliacao()
                ));
            }
            return avaliacaoVOs;
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException("Prestador não encontrado");
        }
    }

    public List<AvaliacaoVO> getAvaliacoesPorPrestadorPorNota(Integer idPrestador, Integer nota) {
        List<AvaliacaoVO> avaliacoes = getAvaliacoesPorPrestador(idPrestador);
        List<AvaliacaoVO> notaCerta = new ArrayList<>();
        for (AvaliacaoVO avaliacao : avaliacoes) {
            if (avaliacao.getNota() >= nota - 0.5 && avaliacao.getNota() <= nota + 0.5) {
                notaCerta.add(avaliacao);
            }
        }
        return notaCerta;
    }

    public List<AvaliacaoVO> getAvaliacoesPorNota(Integer nota) {
        List<Solicitacao> solicitacoes = solicitacaoRepository.findAll();

        if (solicitacoes.isEmpty()) {
            return null;
        }

        List<Solicitacao> avaliadas = new ArrayList<>();
        for (Solicitacao solicitacao : solicitacoes) {
            if (solicitacao.getFkAvaliacao() != null) {
                avaliadas.add(solicitacao);
            }
        }

        List<AvaliacaoVO> notaCerta = new ArrayList<>();
        for (Solicitacao avaliacao : avaliadas) {
            if (avaliacao.getFkAvaliacao().getNota() >= nota - 0.5 && avaliacao.getFkAvaliacao().getNota() <= nota + 0.5) {
                notaCerta.add(new AvaliacaoVO(
                        avaliacao.getFkSolicitante().getId(),
                        avaliacao.getFkSolicitante().getNome(),
                        avaliacao.getFkSolicitante().getImagem(),
                        avaliacao.getFkAvaliacao().getNota(),
                        avaliacao.getFkAvaliacao().getAvaliacao()
                ));
            }
        }
        return notaCerta;
    }

}

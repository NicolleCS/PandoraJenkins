package br.com.pandora.projetopandora.service;

import br.com.pandora.projetopandora.data.model.Prestador;
import br.com.pandora.projetopandora.data.model.Servico;
import br.com.pandora.projetopandora.repository.PrestadorRepository;
import br.com.pandora.projetopandora.repository.ServicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ServicoService {

    @Autowired
    private ServicoRepository servicoRepository;

    @Autowired
    private PrestadorRepository prestadorRepository;

    @Autowired
    private AvaliacaoService avaliacaoService;

    public List<Servico> obterPorNota(double nota) {
        List<Prestador> prestadores = prestadorRepository.findAll();
        List<Prestador> filtrados = new ArrayList<>();
        for (Prestador p : prestadores) {
            double notaObtida = avaliacaoService.getMediaAvaliacoesPorPrestador(p.getId());
            if (notaObtida <= nota + 0.5 && notaObtida >= nota - 0.5) {
                filtrados.add(p);
            }
        }
        List<Servico> servicos = new ArrayList<>();
        for (Prestador f : filtrados) {
            servicos.addAll(servicoRepository.findAllByFkPrestador_Id(f.getId()));
        }
        return servicos;
    }

    public Servico cadastrarServico(Servico servico) {
        return servicoRepository.save(servico);
    }

    public Servico alterarServico(Servico servico) {
        return servicoRepository.save(servico);
    }
}

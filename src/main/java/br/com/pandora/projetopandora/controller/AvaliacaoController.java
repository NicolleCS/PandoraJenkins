package br.com.pandora.projetopandora.controller;

import br.com.pandora.projetopandora.data.dto.AvaliacaoDto;
import br.com.pandora.projetopandora.exception.RegraNegocioException;
import br.com.pandora.projetopandora.data.model.Avaliacao;
import br.com.pandora.projetopandora.service.AvaliacaoService;
import br.com.pandora.projetopandora.data.vo.AvaliacaoVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/avaliacoes")
public class AvaliacaoController {

    @Autowired
    AvaliacaoService avaliacaoService;

    @PostMapping("/novo")
    public ResponseEntity cadastrar(@RequestBody AvaliacaoDto avaliacao) {
        Avaliacao novaAvaliacao;
        try {
            novaAvaliacao = avaliacaoService.criarAvaliacao(avaliacao);
        } catch (RegraNegocioException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        if (novaAvaliacao != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(novaAvaliacao);
        }
        return ResponseEntity.badRequest().build();
    }

    @GetMapping
    public ResponseEntity<List<Avaliacao>> buscarAvaliacoes() {
        List<Avaliacao> avaliacoes = avaliacaoService.buscarTodasAvaliacoes();
        //se avaliações for igual a "null"
        if (avaliacoes == null) {
            ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(avaliacoes);
    }

    @PutMapping("/{id}")
    public ResponseEntity atualizarAvaliacao(@PathVariable("id") Long id, @RequestBody Avaliacao avaliacao) {
        avaliacaoService.atualizarAvaliacao(avaliacao);
        return ResponseEntity.ok(avaliacao);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deletarAvaliacao(@PathVariable("id") Integer id) {
        avaliacaoService.excluirAvaliacao(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/media/{idPrestador}")
    public ResponseEntity<Double> getMediaAvaliacoes(@PathVariable Integer idPrestador) {
        Double media = avaliacaoService.getMediaAvaliacoesPorPrestador(idPrestador);
        return ResponseEntity.ok(media);
    }

    @GetMapping("/minhas_avaliacoes/{idPrestador}")
    public ResponseEntity<Object> getAvaliacoesPorPrestador(@PathVariable Integer idPrestador) {
        try {
            List<AvaliacaoVO> avaliacoes = avaliacaoService.getAvaliacoesPorPrestador(idPrestador);
            return ResponseEntity.ok(avaliacoes);
        } catch (NoSuchElementException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/minhas_avaliacoes/por_nota/{idPrestador}/{nota}")
    public ResponseEntity<Object> getAvaliacoesPorPrestadorPorNota(@PathVariable Integer idPrestador, @PathVariable Integer nota) {
        try {
            List<AvaliacaoVO> avaliacoes = avaliacaoService.getAvaliacoesPorPrestadorPorNota(idPrestador, nota);
            return ResponseEntity.ok(avaliacoes);
        } catch (NoSuchElementException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/todos/por_nota/{nota}")
    public ResponseEntity<Object> getAvaliacoesPorNota(@PathVariable Integer nota) {
        try {
            List<AvaliacaoVO> avaliacoes = avaliacaoService.getAvaliacoesPorNota(nota);
            return ResponseEntity.ok(avaliacoes);
        } catch (NoSuchElementException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}

package br.com.pandora.projetopandora.controller;

import br.com.pandora.projetopandora.data.dto.SolicitacaoDto;
import br.com.pandora.projetopandora.exception.ErroCadastroException;
import br.com.pandora.projetopandora.exception.ErroSolicitacaoException;
import br.com.pandora.projetopandora.exception.RegraNegocioException;
import br.com.pandora.projetopandora.data.model.Arquivo;
import br.com.pandora.projetopandora.data.model.Solicitacao;
import br.com.pandora.projetopandora.data.model.StatusSolicitacaoEnum;
import br.com.pandora.projetopandora.service.ArquivoService;
import br.com.pandora.projetopandora.service.SolicitacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import static org.springframework.http.ResponseEntity.*;
import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/solicitacoes")
public class SolicitacaoController {

    @Autowired
    private SolicitacaoService solicitacaoService;

    @Autowired
    private ArquivoService arquivoService;

    @PostMapping("/nova_solicitacao/{idPrestador}/{idSolicitante}")
    public ResponseEntity<Object> solicitarServico(@PathVariable("idPrestador") Integer idPrestador,
                                                   @PathVariable("idSolicitante") Integer idSolicitante,
                                                   @RequestBody Solicitacao pedido) {
        try {
            Solicitacao solicitacao = solicitacaoService.criarSolicitacao(pedido, idPrestador, idSolicitante);
            return ResponseEntity.status(HttpStatus.CREATED).body(solicitacao);
        } catch (ErroCadastroException | RegraNegocioException | ErroSolicitacaoException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/atualizar_solicitacao/{idSolicitacao}")
    public ResponseEntity<Object> alterarStatus(@PathVariable("idSolicitacao") Integer idSolicitacao, @RequestBody SolicitacaoDto newStatus) {
        try {
            StatusSolicitacaoEnum status = StatusSolicitacaoEnum.valueOf(newStatus.getStatus().toUpperCase());
            Solicitacao solicitacao = solicitacaoService.alterarStatus(idSolicitacao, status);
            return ResponseEntity.ok(solicitacao);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/buscar/{id}")
    public ResponseEntity<Object> buscarPorStatus(@RequestParam(required = true) Boolean isSolicitante, @RequestParam(required = false) String status, @PathVariable Integer id) {
        try {
            List<Solicitacao> solicitacoes = new ArrayList<>();
            if (isSolicitante) {
                solicitacoes = status == null ? solicitacaoService.buscarPorSolicitante(id) : solicitacaoService.buscarPorStatusESolicitante(status, id);
            } else {
                solicitacoes = status == null ? solicitacaoService.buscarPorPrestador(id) : solicitacaoService.buscarPorStatusEPrestador(status, id);
            }
            return solicitacoes.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(solicitacoes);
        } catch (NoSuchElementException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("solicitante/buscar/{id}")
    public ResponseEntity<Object> buscarPorStatusPorSolicitante(@RequestParam(required = false) String status, @PathVariable Integer id) {
        try {
            List<Solicitacao> solicitacoes = status == null ? solicitacaoService.buscarPorSolicitante(id) : solicitacaoService.buscarPorStatusESolicitante(status, id);
            return solicitacoes.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(solicitacoes);
        } catch (NoSuchElementException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("prestador/buscar/{id}")
    public ResponseEntity<Object> buscarPorStatusPorPrestador(@RequestParam(required = false) String status, @PathVariable Integer id) {
        try {
            List<Solicitacao> solicitacoes = status == null ? solicitacaoService.buscarPorPrestador(id) : solicitacaoService.buscarPorStatusEPrestador(status, id);
            return solicitacoes.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(solicitacoes);
        } catch (NoSuchElementException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("visualizar-arquivo/{id}")
        public ResponseEntity mostrarArquivo(@PathVariable Integer id){
        List<Solicitacao> solicitacoes = solicitacaoService.buscarPorPrestador(id);
        if (!solicitacoes.isEmpty()) {
            Arquivo arquivo = arquivoService.montarArquivo(id);
            return ok(arquivo.toString());
        }
        return badRequest().build();
    }

    @GetMapping(value = "gerar-arquivo/{id}", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public ResponseEntity<byte[]> gerarArquivo(@PathVariable Integer id) throws IOException {
        File file = new File("./src/main/resources/minhas-solicitacoes.txt");
        PrintWriter printWriter = new PrintWriter(file);
        printWriter.print("");
        printWriter.close();
        List<Solicitacao> solicitacoes = solicitacaoService.buscarPorPrestador(id);
        if (!solicitacoes.isEmpty()) {
            Arquivo arquivo = arquivoService.montarArquivo(id);
            arquivoService.gravarArquivo(arquivo);
            return ok(Files.readAllBytes(file.toPath()));
        }
        return badRequest().build();
    }
}

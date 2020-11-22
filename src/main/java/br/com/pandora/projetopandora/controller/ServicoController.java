package br.com.pandora.projetopandora.controller;

import br.com.pandora.projetopandora.data.dto.ServicoDto;
import br.com.pandora.projetopandora.exception.ErroCadastroException;
import br.com.pandora.projetopandora.data.model.CategoriaServico;
import br.com.pandora.projetopandora.data.model.Prestador;
import br.com.pandora.projetopandora.data.model.Servico;
import br.com.pandora.projetopandora.repository.ServicoRepository;
import br.com.pandora.projetopandora.service.AvaliacaoService;
import br.com.pandora.projetopandora.service.CategoriaServicoService;
import br.com.pandora.projetopandora.service.PrestadorService;
import br.com.pandora.projetopandora.service.ServicoService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.ResponseEntity.*;


@RestController
@RequestMapping("/servicos")
@RequiredArgsConstructor
public class ServicoController {

    @Autowired
    private ServicoService servicoService;
    @Autowired
    private ServicoRepository repository;
    @Autowired
    private PrestadorService prestadorService;
    @Autowired
    private CategoriaServicoService categoriaServicoService;
    @Autowired
    private AvaliacaoService avaliacaoService;

    @PostMapping("/cadastrar")
    public ResponseEntity<Object> cadastrarServico(@RequestBody ServicoDto dto) {
        try {
            Servico servico = converter(dto);
            servico = servicoService.cadastrarServico(servico);
            return new ResponseEntity<Object>(servico, HttpStatus.CREATED);
        } catch (ErroCadastroException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/buscar-categoria/{id}")
    public ResponseEntity buscarPorCategoria(@PathVariable Integer id) {
        List servicos = repository.findAllByFkCategoriaServicoIdCategoria(id);
        return servicos.isEmpty() ? noContent().build() : ok(servicos);
    }

    @GetMapping("/todos")
    public ResponseEntity buscarServicos() {
        List servicos = repository.findAll();
        return servicos.isEmpty() ? noContent().build() : ok(servicos);
    }

    @GetMapping("/busca-palavra")
    public ResponseEntity buscarPorPalavra(@RequestParam String filtro) {

        List servicos = repository.findAllByPalavra(filtro);

        return servicos.isEmpty() ? noContent().build() : ok(servicos);

    }

    @DeleteMapping("/delete-id/{id}")
    public ResponseEntity deletarPorId(@PathVariable Integer id) {
        if (repository.existsById(id)) {
            repository.deleteByIdServico(id);
            return ok().build();
        } else {
            return notFound().build();
        }
    }

    @PutMapping("/alterar-servico/{id}")
    public ResponseEntity alterarServico(@PathVariable Integer id, @RequestBody ServicoDto servicoAlterado) {
        if (repository.existsById(id)) {
            Servico servico = converter(servicoAlterado);
            servico.setIdServico(id);
            servicoService.alterarServico(servico);
            return ok().build();
        } else {
            return notFound().build();
        }
    }

    @GetMapping("/meus-servicos/{id}")
    public ResponseEntity getMeusServicos(@PathVariable Integer id) {

        List servicos = repository.findAllByFkPrestador_Id(id);

        return servicos.isEmpty() ? noContent().build() : ok(servicos);
    }

    @GetMapping("/por-id/{id}")
    public ResponseEntity getServicoPorId(@PathVariable Integer id) {

        List servico = repository.findAllByIdServico(id);

        return servico.isEmpty() ? noContent().build() : ok(servico);
    }

    @GetMapping("/por-nota/{nota}")
    public ResponseEntity getServicoPorNota(@PathVariable Integer nota) {
        List<Servico> lista = servicoService.obterPorNota(nota);
        if(lista.isEmpty()){
            return ResponseEntity.noContent().build();
        }else {
            return ResponseEntity.ok(lista);
        }

    }


    private Servico converter(ServicoDto dto) {

        Servico servico = new Servico();
        servico.setDescricao(dto.getDescricao());
        servico.setTitulo(dto.getTitulo());
        servico.setImagem(dto.getImagem());

        Prestador prestador = prestadorService.obterPorId(dto.getPrestador())
                .orElseThrow(() -> new ErroCadastroException("Prestador não existente"));

        servico.setFkPrestador(prestador);

        CategoriaServico categoriaServico = categoriaServicoService.obterPorId(dto.getCategoriaServico())
                .orElseThrow(() -> new ErroCadastroException("Categoria do serviço não existente"));

        servico.setFkCategoriaServico(categoriaServico);

        return servico;
    }

}

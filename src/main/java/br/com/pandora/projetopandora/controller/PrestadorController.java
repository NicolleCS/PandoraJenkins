package br.com.pandora.projetopandora.controller;

import br.com.pandora.projetopandora.data.dto.UsuarioDto;
import br.com.pandora.projetopandora.exception.ErroAlteracaoException;
import br.com.pandora.projetopandora.exception.ErroCadastroException;
import br.com.pandora.projetopandora.data.model.Prestador;
import br.com.pandora.projetopandora.repository.PrestadorRepository;
import br.com.pandora.projetopandora.service.PrestadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.ResponseEntity.noContent;
import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/prestadores")
public class PrestadorController {

    @Autowired
    private PrestadorService prestadorService;

    @Autowired
    private PrestadorRepository repository;

    @PostMapping("/cadastrar")
    public ResponseEntity<Object> cadastrar(@RequestBody Prestador prestador) {
        try {
            Prestador novoPrestador = prestadorService.cadastrar(prestador);
            return ResponseEntity.status(HttpStatus.CREATED).body(novoPrestador);
        }
        catch (ErroCadastroException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/alterar_email/{id_prestador}")
    public ResponseEntity<Object> alterarEmail(@RequestBody UsuarioDto novo, @PathVariable("id_prestador") Integer id) {
        try {
            Prestador alterado = prestadorService.alterarEmail(id, novo);
            return ResponseEntity.ok(alterado);
        } catch (ErroAlteracaoException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/alterar_telefone/{id}")
    public ResponseEntity<Object> alterarTelefone(@RequestBody UsuarioDto novo, @PathVariable Integer id) {
        try {
            Prestador alterado = prestadorService.alterarTelefone(id, novo);
            return ResponseEntity.ok(alterado);
        } catch (ErroAlteracaoException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/alterar_senha/{id}")
    public ResponseEntity<Object> alterarSenha(@RequestBody UsuarioDto novo, @PathVariable Integer id) {
        try {
            Prestador alterado = prestadorService.alterarSenha(id, novo);
            return ResponseEntity.ok(alterado);
        } catch (ErroAlteracaoException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/alterar_imagem/{id}")
    public ResponseEntity<Object> alterarImagem(@RequestBody UsuarioDto novo, @PathVariable Integer id) {
        try {
            Prestador alterado = prestadorService.alterarImagem(id, novo);
            return ResponseEntity.ok(alterado);
        } catch (ErroAlteracaoException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


    @PutMapping("/alterar_cep/{id}")
    public ResponseEntity<Object> alterarCep(@RequestBody UsuarioDto novo, @PathVariable Integer id) {
        try {
            Prestador alterado = prestadorService.alterarCep(id, novo);
            return ResponseEntity.ok(alterado);
        } catch (ErroAlteracaoException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


    @GetMapping("/minhas-avaliacoes/{id}")
    public ResponseEntity getMinhasAvaliacoes(@PathVariable Integer id){

        List avaliacoes = repository.findAllAvaliacoes(id);

        return  avaliacoes.isEmpty() ? noContent().build() : ok(avaliacoes);

    }

//    @GetMapping("/por-id/{id}")
//    public ResponseEntity getServicoPorId(@PathVariable Integer id){
//
//        List servico = repository.findAllByIdServico(id);
//
//        return  servico.isEmpty() ? noContent().build() : ok(servico);
//    }
}

package br.com.pandora.projetopandora.controller;

import br.com.pandora.projetopandora.data.dto.UsuarioDto;
import br.com.pandora.projetopandora.exception.ErroAlteracaoException;
import br.com.pandora.projetopandora.exception.ErroCadastroException;
import br.com.pandora.projetopandora.data.model.Solicitante;
import br.com.pandora.projetopandora.service.EnderecoService;
import br.com.pandora.projetopandora.service.SolicitanteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/solicitantes")
public class SolicitanteController {

    @Autowired
    private SolicitanteService solicitanteService;

    @Autowired
    private EnderecoService enderecoService;

    @PostMapping("/cadastrar")
    public ResponseEntity<Object> cadastrar(@RequestBody Solicitante usuario) {
        try {
            Solicitante solicitanteCadastrado = solicitanteService.cadastrar(usuario);
            return ResponseEntity.status(HttpStatus.CREATED).body(solicitanteCadastrado);
        } catch (ErroCadastroException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/alterar_email/{id_solicitante}")
    public ResponseEntity<Object> alterarEmail(@RequestBody UsuarioDto novo, @PathVariable("id_solicitante") Integer id) {
        try {
            Solicitante alterado = solicitanteService.alterarEmail(id, novo);
            return ResponseEntity.ok(alterado);
        } catch (ErroAlteracaoException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/alterar_telefone/{id}")
    public ResponseEntity<Object> alterarTelefone(@RequestBody UsuarioDto novo, @PathVariable Integer id) {
        try {
            Solicitante alterado = solicitanteService.alterarTelefone(id, novo);
            return ResponseEntity.ok(alterado);
        } catch (ErroAlteracaoException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/alterar_senha/{id}")
    public ResponseEntity<Object> alterarSenhar(@RequestBody UsuarioDto novo, @PathVariable Integer id) {
        try {
            Solicitante alterado = solicitanteService.alterarSenha(id, novo);
            return ResponseEntity.ok(alterado);
        } catch (ErroAlteracaoException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/alterar_imagem/{id}")
    public ResponseEntity<Object> alterarImagem(@RequestBody UsuarioDto novo, @PathVariable Integer id) {
        try {
            Solicitante alterado = solicitanteService.alterarImagem(id, novo);
            return ResponseEntity.ok(alterado);
        } catch (ErroAlteracaoException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


}

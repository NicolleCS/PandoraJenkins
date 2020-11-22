package br.com.pandora.projetopandora.controller;

import br.com.pandora.projetopandora.data.dto.UsuarioDto;
import br.com.pandora.projetopandora.exception.RegraNegocioException;
import br.com.pandora.projetopandora.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;


    @PostMapping("/logar")
    public ResponseEntity<Object> login(@RequestBody UsuarioDto user) {
        try {
            Optional<?> usuario = usuarioService.logar(user.getEmail(), user.getSenha());
            return ResponseEntity.ok(usuario);
        } catch (RegraNegocioException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }
    

}

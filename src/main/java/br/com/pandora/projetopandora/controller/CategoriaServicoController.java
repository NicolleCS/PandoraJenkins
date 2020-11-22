package br.com.pandora.projetopandora.controller;

import br.com.pandora.projetopandora.data.model.CategoriaServico;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import br.com.pandora.projetopandora.service.CategoriaServicoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/categoria-servico")
@RequiredArgsConstructor
public class CategoriaServicoController {

    @Autowired
    private CategoriaServicoService service;

    @GetMapping
    public ResponseEntity obterTodos() {
        List<CategoriaServico> categorias = service.obterTodos();
        return categorias.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(categorias);
    }
}

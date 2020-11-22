package br.com.pandora.projetopandora.controller;

import br.com.pandora.projetopandora.clients.ClientApilayer;
import br.com.pandora.projetopandora.clients.ClientViaCep;
import br.com.pandora.projetopandora.data.dto.EmailDto;
import br.com.pandora.projetopandora.data.model.Cep;
import br.com.pandora.projetopandora.data.model.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/consultas")
public class ConsultasController {

    @Autowired
    private ClientViaCep clienteViaCep;

    @Autowired
    private ClientApilayer clientApilayer;

    @GetMapping("/cep/{cep}")
    public ResponseEntity buscarCep(@PathVariable String cep) {
        Cep encontrado = clienteViaCep.getCep(cep);
        if (encontrado != null) {
            return ResponseEntity.ok(encontrado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/email")
    public ResponseEntity buscarEmail(@RequestBody EmailDto emailDto) {
        EmailValidator validado = clientApilayer.validarEmail(
                "815e68d85a252e71be6b013b80a8d874",
                emailDto.getEmail(),
                1,
                1);
        return ResponseEntity.ok(validado);
    }
}

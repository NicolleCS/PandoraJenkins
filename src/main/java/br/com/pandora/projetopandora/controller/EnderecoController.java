package br.com.pandora.projetopandora.controller;

import br.com.pandora.projetopandora.data.dto.EnderecoDto;
import br.com.pandora.projetopandora.exception.ErroCadastroException;
import br.com.pandora.projetopandora.data.model.Endereco;
import br.com.pandora.projetopandora.data.model.Solicitante;
import br.com.pandora.projetopandora.repository.EnderecoRepository;
import br.com.pandora.projetopandora.service.EnderecoService;
import br.com.pandora.projetopandora.service.SolicitanteService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.ResponseEntity.*;

@RestController
@RequestMapping("/enderecos")
@RequiredArgsConstructor
public class EnderecoController {

    @Autowired
    private EnderecoService enderecoService;

    @Autowired
    private SolicitanteService solicitanteService;

    @Autowired
    private EnderecoRepository repository;

    @PostMapping("/cadastrar")
    public ResponseEntity<Object> cadastrarEndereco(@RequestBody EnderecoDto dto) {
        try {
            Endereco endereco = converter(dto);
            endereco = enderecoService.cadastrarEndereco(endereco);
            return new ResponseEntity<Object>(endereco, HttpStatus.CREATED);

        } catch (ErroCadastroException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/buscar-solicitante/{id}")
    public ResponseEntity buscarPorId(@PathVariable("id") Integer id) {
        List enderecos = repository.findAllByFkSolicitante_Id(id);
        return enderecos.isEmpty() ? noContent().build() : ok(enderecos);

    }

    @PutMapping("/alterar-endereco/{id}")
    public ResponseEntity alterarServico(@PathVariable Integer id, @RequestBody EnderecoDto enderecoAlterado) {
        if (repository.existsById(id)) {
            Endereco endereco = converter(enderecoAlterado);
            endereco.setId(id);
            enderecoService.alterarEndereco(endereco);
            return ok().build();
        } else {
            return notFound().build();
        }
    }

    private Endereco converter(EnderecoDto dto) {
        Endereco endereco = new Endereco();
        endereco.setBairro(dto.getBairro());
        endereco.setCep(dto.getCep());
        endereco.setComplemento(dto.getComplemento());
        endereco.setNumero(dto.getNumero());
        endereco.setLogradouro(dto.getLogradouro());
        endereco.setUf(dto.getUf());
        endereco.setLocalidade(dto.getLocalidade());

        Solicitante solicitante = solicitanteService.obterPorId(dto.getUsuario())
                .orElseThrow(() -> new ErroCadastroException("Usuário não existente"));

        endereco.setFkSolicitante(solicitante);
        return endereco;
    }
}

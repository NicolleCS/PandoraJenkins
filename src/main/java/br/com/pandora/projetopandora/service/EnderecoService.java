package br.com.pandora.projetopandora.service;

import br.com.pandora.projetopandora.data.model.Endereco;
import br.com.pandora.projetopandora.repository.EnderecoRepository;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EnderecoService {

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Autowired
    private SolicitanteService solicitanteService;

    @Getter @Setter
    private static Endereco enderecoCadastro;

//    public Endereco cadastrarEndereco(Endereco endereco) {
//        endereco.setFkSolicitante(SolicitanteService.getInformacoesPessoais());
//        enderecoCadastro = endereco;
//        return enderecoCadastro;
//    }

    public Endereco cadastrarEndereco(Endereco endereco) {
        return  enderecoRepository.save(endereco);
    }

    public Endereco alterarEndereco(Endereco endereco) {
        return  enderecoRepository.save(endereco);
    }
}

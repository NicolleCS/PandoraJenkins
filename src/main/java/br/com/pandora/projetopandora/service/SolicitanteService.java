package br.com.pandora.projetopandora.service;

import br.com.pandora.projetopandora.data.dto.UsuarioDto;
import br.com.pandora.projetopandora.exception.ErroAlteracaoException;
import br.com.pandora.projetopandora.helper.UsuarioHelper;
import br.com.pandora.projetopandora.data.model.Solicitante;
import br.com.pandora.projetopandora.repository.SolicitanteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SolicitanteService extends UsuarioHelper {

    @Autowired
    private SolicitanteRepository solicitanteRepository;


    public Optional<Solicitante> logar(String email) {
        return solicitanteRepository.findByEmail(email);
    }


    public Solicitante cadastrar(Solicitante solicitante) {
        if (null == solicitante.getCnpj()) {
            validarInformacoesCadastroSemCnpj(solicitante.getEmail(), solicitante.getCpf());
        } else {
            validarInformacoesCadastro(solicitante.getEmail(), solicitante.getCpf(), solicitante.getCnpj());
        }
        return solicitanteRepository.save(solicitante);
    }

    public Solicitante alterarEmail(Integer id, UsuarioDto novo) {
        Optional<Solicitante> solicitante = solicitanteRepository.getById(id);
        if (solicitante.isPresent()) {
            solicitante.get().setEmail(novo.getEmail());
            return solicitanteRepository.save(solicitante.get());
        }
        throw new ErroAlteracaoException("Usuário não encontrado");
    }

    public Solicitante alterarTelefone(Integer id, UsuarioDto novo) {
        Optional<Solicitante> solicitante = solicitanteRepository.getById(id);
        if (solicitante.isPresent()) {
            solicitante.get().setTelefone(novo.getTelefone());
            return solicitanteRepository.save(solicitante.get());
        }
        throw new ErroAlteracaoException("Usuário não encontrado");
    }

    public Solicitante alterarSenha(Integer id, UsuarioDto novo) {
        Optional<Solicitante> solicitante = solicitanteRepository.getById(id);
        if (solicitante.isPresent()) {
            solicitante.get().setSenha(novo.getSenha());
            return solicitanteRepository.save(solicitante.get());
        }
        throw new ErroAlteracaoException("Usuário não encontrado");
    }

    public Solicitante alterarImagem(Integer id, UsuarioDto novo) {
        Optional<Solicitante> solicitante = solicitanteRepository.getById(id);
        if (solicitante.isPresent()) {
            solicitante.get().setImagem(novo.getImagem());
            return solicitanteRepository.save(solicitante.get());
        }
        throw new ErroAlteracaoException("Usuário não encontrado");
    }

    public Optional<Solicitante> obterPorId(Integer id) {
        return solicitanteRepository.getById(id);
    }

    public Optional<Solicitante> obterPorEmail(String email) {
        return solicitanteRepository.getByEmail(email);
    }

    @Override
    public Optional<? extends Object> validarEmail(String email) {
        return solicitanteRepository.findByEmail(email);
    }

    @Override
    public Optional<? extends Object> validarCpf(String cpf) {
        return solicitanteRepository.findByCpf(cpf);
    }

    @Override
    public Optional<? extends Object> validarCnpj(String cnpj) {
        return solicitanteRepository.findByCnpj(cnpj);
    }
}

package br.com.pandora.projetopandora.service;

import br.com.pandora.projetopandora.data.dto.UsuarioDto;
import br.com.pandora.projetopandora.exception.ErroAlteracaoException;
import br.com.pandora.projetopandora.helper.UsuarioHelper;
import br.com.pandora.projetopandora.data.model.Prestador;
import br.com.pandora.projetopandora.repository.PrestadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PrestadorService extends UsuarioHelper {

    @Autowired
    private PrestadorRepository prestadorRepository;

    public Optional<Prestador> logar(String email) {
        return prestadorRepository.findByEmail(email);
    }

    public Prestador cadastrar(Prestador prestador) {
        if (null == prestador.getCnpj()) {
            validarInformacoesCadastroSemCnpj(prestador.getEmail(), prestador.getCpf());
        } else {
            validarInformacoesCadastro(prestador.getEmail(), prestador.getCpf(), prestador.getCnpj());
        }
        return prestadorRepository.save(prestador);
    }

    public Prestador alterarEmail(Integer id, UsuarioDto novo) {
        Optional<Prestador> prestador = prestadorRepository.getById(id);
        if (prestador.isPresent()) {
            prestador.get().setEmail(novo.getEmail());
            return prestadorRepository.save(prestador.get());
        }
        throw new ErroAlteracaoException("Usuário não encontrado");
    }


    public Prestador alterarTelefone(Integer id, UsuarioDto novo) {
        Optional<Prestador> prestador = prestadorRepository.getById(id);
        if (prestador.isPresent()) {
            prestador.get().setTelefone(novo.getTelefone());
            return prestadorRepository.save(prestador.get());
        }
        throw new ErroAlteracaoException("Usuário não encontrado");
    }

    public Prestador alterarSenha(Integer id, UsuarioDto novo) {
        Optional<Prestador> prestador = prestadorRepository.getById(id);
        if (prestador.isPresent()) {
            prestador.get().setSenha(novo.getSenha());
            return prestadorRepository.save(prestador.get());
        }
        throw new ErroAlteracaoException("Usuário não encontrado");
    }

    public Prestador alterarImagem(Integer id, UsuarioDto novo) {
        Optional<Prestador> prestador = prestadorRepository.getById(id);
        if (prestador.isPresent()) {
            prestador.get().setImagem(novo.getImagem());
            return prestadorRepository.save(prestador.get());
        }
        throw new ErroAlteracaoException("Usuário não encontrado");
    }

    public Prestador alterarCep(Integer id, UsuarioDto novo) {
        Optional<Prestador> prestador = prestadorRepository.getById(id);
        if (prestador.isPresent()) {
            prestador.get().setCep(novo.getCep());
            return prestadorRepository.save(prestador.get());
        }
        throw new ErroAlteracaoException("Usuário não encontrado");
    }

    @Override
    public Optional<Prestador> validarEmail(String email) {
        return prestadorRepository.findByEmail(email);
    }

    @Override
    public Optional<Prestador> validarCpf(String cpf) {
        return prestadorRepository.findByCpf(cpf);
    }

    @Override
    public Optional<Prestador> validarCnpj(String cnpj) {
        return prestadorRepository.findByCnpj(cnpj);
    }

    public Optional<Prestador> obterPorId(Integer id) {
        return prestadorRepository.getById(id);
    }

    public Optional<Prestador> obterPorEmail(String email) {
        return prestadorRepository.getByEmail(email);
    }
}

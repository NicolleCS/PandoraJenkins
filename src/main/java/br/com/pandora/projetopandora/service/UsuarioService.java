package br.com.pandora.projetopandora.service;

import br.com.pandora.projetopandora.data.dto.UsuarioDto;
import br.com.pandora.projetopandora.exception.RegraNegocioException;
import br.com.pandora.projetopandora.data.model.Prestador;
import br.com.pandora.projetopandora.data.model.Solicitante;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class UsuarioService {

    @Autowired
    private SolicitanteService solicitanteService;

    @Autowired
    private PrestadorService prestadorService;

    public Optional<? extends UsuarioDto> logar(String email, String senha) {
        Optional<Solicitante> solicitante = solicitanteService.logar(email);
        Optional<Prestador> prestador;
        if (solicitante.isPresent()) {
            if (senha.equals(solicitante.get().getSenha())) {
                return solicitante;
            } else {
                throw new RegraNegocioException("Senha incorreta!");
            }
        } else {
            prestador = prestadorService.logar(email);
            if (prestador.isPresent()) {
                if (senha.equals(prestador.get().getSenha())) {
                    return prestador;
                } else {
                    throw new RegraNegocioException(("Senha incorreta!"));
                }
            }
        }
        throw new RegraNegocioException("E-mail inválido!");
    }

}

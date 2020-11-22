package br.com.pandora.projetopandora.service;

import br.com.pandora.projetopandora.data.dto.UsuarioDto;
import br.com.pandora.projetopandora.data.model.Prestador;
import br.com.pandora.projetopandora.data.model.Solicitante;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuxiliarServices {
    @Autowired
    private SolicitanteService solicitanteService;

    @Autowired
    private PrestadorService prestadorService;

    public void trocarSenha(String senha, String email) throws Exception {
        UsuarioDto dto = new UsuarioDto();
        dto.setSenha(senha);
        Optional<Solicitante> user = solicitanteService.obterPorEmail(email);
        if (user.isPresent()) {
            solicitanteService.alterarSenha(user.get().getId(), dto);
        } else {
            Optional<Prestador> prest = prestadorService.obterPorEmail(email);
            if(prest.isEmpty()){
                throw new Exception("Usuário inexistente");
            }
            prestadorService.alterarSenha(prest.get().getId(), dto);
        }
    }


}

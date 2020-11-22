package br.com.pandora.projetopandora.data.dto;

import lombok.*;

@Getter
@Setter
public class UsuarioDto {

    private String email;
    private String senha;
    private String telefone;
    private String imagem;
    private String cep;

}

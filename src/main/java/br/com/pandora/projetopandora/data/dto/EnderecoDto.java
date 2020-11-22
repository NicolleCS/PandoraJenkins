package br.com.pandora.projetopandora.data.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EnderecoDto {
    private Integer id;
    private String logradouro;
    private String numero;
    private String complemento;
    private String cep;
    private String bairro;
    private String uf;
    private String localidade;
    private Integer usuario;
}

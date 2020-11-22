package br.com.pandora.projetopandora.data.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ServicoDto {

    private Integer id;
    private String descricao;
    private String titulo;
    private Integer prestador;
    private Integer categoriaServico;
    private String imagem;

}

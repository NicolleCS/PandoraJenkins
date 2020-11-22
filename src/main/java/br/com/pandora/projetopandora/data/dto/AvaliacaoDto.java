package br.com.pandora.projetopandora.data.dto;

import lombok.*;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AvaliacaoDto {

    private Double nota;

    private String avaliacao;

    private Integer idSolicitacao;

}

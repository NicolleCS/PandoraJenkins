package br.com.pandora.projetopandora.data.vo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AvaliacaoVO {

    private Integer idSolicitante;

    private String nomeSolicitante;

    private String imgPerfilSolicitante;

    private Double nota;

    private String avaliacao;

    public AvaliacaoVO(Integer idSolicitante, String nomeSolicitante, String imgPerfilSolicitante, Double nota, String avaliacao) {
        this.idSolicitante = idSolicitante;
        this.nomeSolicitante = nomeSolicitante;
        this.imgPerfilSolicitante = imgPerfilSolicitante;
        this.nota = nota;
        this.avaliacao = avaliacao;
    }
}

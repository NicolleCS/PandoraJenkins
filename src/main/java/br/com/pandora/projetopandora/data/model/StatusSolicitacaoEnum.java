package br.com.pandora.projetopandora.data.model;

import lombok.Getter;

@Getter
public enum StatusSolicitacaoEnum {
    SOLICITADO(0),
    APROVADO(1),
    EXECUCAO(2),
    FECHADO(3),
    CANCELADO(4);

    private Integer codigo;

    StatusSolicitacaoEnum(Integer codigo) {
        this.codigo = codigo;
    }
}

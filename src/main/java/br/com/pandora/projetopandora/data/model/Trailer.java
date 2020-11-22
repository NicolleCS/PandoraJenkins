package br.com.pandora.projetopandora.data.model;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class Trailer {

    private Integer qtdRegistro;

    public Trailer(Integer qtdRegistro) {
        this.qtdRegistro = qtdRegistro;
    }

    @Override
    public String toString() {
        return String.format("02%03d", getQtdRegistro());
    }

    public Integer getQtdRegistro() {
        return qtdRegistro;
    }

    public void setQtdRegistro(Integer qtdRegistro) {
        this.qtdRegistro = qtdRegistro;
    }
}

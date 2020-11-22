package br.com.pandora.projetopandora.data.model;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class Header {

    private Integer idPrestador;
    private String nomePrestador;
    private String documentoPrestador;
    private String dataHoraCriacao;
    private String versaoLayout;

    public Header(Integer idPrestador, String nomePrestador, String documentoPrestador, String dataHoraCriacao, String versaoLayout) {
        this.idPrestador = idPrestador;
        this.nomePrestador = nomePrestador;
        this.documentoPrestador = documentoPrestador;
        this.dataHoraCriacao = dataHoraCriacao;
        this.versaoLayout = versaoLayout;
    }

    @Override
    public String toString() {
        return String.format("00%04d%-40s%-14s%19s01%n", getIdPrestador(), getNomePrestador(),
                getDocumentoPrestador(), getDataHoraCriacao());
    }

    public Integer getIdPrestador() {
        return idPrestador;
    }

    public void setIdPrestador(Integer idPrestador) {
        this.idPrestador = idPrestador;
    }

    public String getNomePrestador() {
        return nomePrestador;
    }

    public void setNomePrestador(String nomePrestador) {
        this.nomePrestador = nomePrestador;
    }

    public String getDocumentoPrestador() {
        return documentoPrestador;
    }

    public void setDocumentoPrestador(String documentoPrestador) {
        this.documentoPrestador = documentoPrestador;
    }

    public String getDataHoraCriacao() {
        return dataHoraCriacao;
    }

    public void setDataHoraCriacao(String dataHoraCriacao) {
        this.dataHoraCriacao = dataHoraCriacao;
    }

    public String getVersaoLayout() {
        return versaoLayout;
    }

    public void setVersaoLayout(String versaoLayout) {
        this.versaoLayout = versaoLayout;
    }
}

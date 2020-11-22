package br.com.pandora.projetopandora.data.model;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class Body {

    private String tipoRegistro;
    private Integer idSolicitante;
    private String nomeSolicitante;
    private String documentoSolicitante;
    private String descricaoSolicitacao;
    private Double nota;
    private String avalicao;

    public Body(String tipoRegistro, Integer idSolicitante, String nomeSolicitante, String documentoSolicitante, String descricaoSolicitacao, Double nota, String avalicao) {
        this.tipoRegistro = tipoRegistro;
        this.idSolicitante = idSolicitante;
        this.nomeSolicitante = nomeSolicitante;
        this.documentoSolicitante = documentoSolicitante;
        this.descricaoSolicitacao = descricaoSolicitacao;
        this.nota = nota;
        this.avalicao = avalicao;
    }

    @Override
    public String toString() {
        if(getNota() == 0.0 && getAvalicao().equals("")){
            return String.format("01%04d%-40s%14s%-150s%-5s%-280s%n", getIdSolicitante(), getNomeSolicitante(),
                    getDocumentoSolicitante(), getDescricaoSolicitacao(), "", "");
        }
        return String.format("01%04d%-40s%14s%-150s%5.2f%-280s%n", getIdSolicitante(), getNomeSolicitante(),
                getDocumentoSolicitante(), getDescricaoSolicitacao(), getNota(), getAvalicao());
    }

    public String getTipoRegistro() {
        return tipoRegistro;
    }

    public void setTipoRegistro(String tipoRegistro) {
        this.tipoRegistro = tipoRegistro;
    }

    public Integer getIdSolicitante() {
        return idSolicitante;
    }

    public void setIdSolicitante(Integer idSolicitante) {
        this.idSolicitante = idSolicitante;
    }

    public String getNomeSolicitante() {
        return nomeSolicitante;
    }

    public void setNomeSolicitante(String nomeSolicitante) {
        this.nomeSolicitante = nomeSolicitante;
    }

    public String getDocumentoSolicitante() {
        return documentoSolicitante;
    }

    public void setDocumentoSolicitante(String documentoSolicitante) {
        this.documentoSolicitante = documentoSolicitante;
    }

    public String getDescricaoSolicitacao() {
        return descricaoSolicitacao;
    }

    public void setDescricaoSolicitacao(String descricaoSolicitacao) {
        this.descricaoSolicitacao = descricaoSolicitacao;
    }

    public Double getNota() {
        return nota;
    }

    public void setNota(Double nota) {
        this.nota = nota;
    }

    public String getAvalicao() {
        return avalicao;
    }

    public void setAvalicao(String avalicao) {
        this.avalicao = avalicao;
    }


}

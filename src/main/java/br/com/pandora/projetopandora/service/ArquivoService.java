package br.com.pandora.projetopandora.service;

import br.com.pandora.projetopandora.data.model.*;
import br.com.pandora.projetopandora.repository.SolicitacaoRepository;
import br.com.pandora.projetopandora.util.FilaObj;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class ArquivoService {

    @Autowired
    SolicitacaoRepository solicitacaoRepository;

    public void gravaRegistro (String nomeArq, String registro) {
        BufferedWriter saida = null;
        try {
            saida = new BufferedWriter(new FileWriter(nomeArq, true));
        } catch (IOException e) {
            System.err.printf("Erro na abertura do arquivo: %s.\n", e.getMessage());
        }
        try {
            saida.append(registro + "\n");
            saida.close();
        } catch (IOException e) {
            System.err.printf("Erro ao gravar arquivo: %s.\n", e.getMessage());
        }
    }

    public Arquivo montarArquivo(Integer id){
        Arquivo arquivo = new Arquivo();
        List<Solicitacao> solicitacoes = solicitacaoRepository.findAllByFkPrestador_Id(id);
        Header header = gerarHeader(solicitacoes);
        FilaObj<Body> fila = gerarBody(solicitacoes);
        Trailer trailer = gerarTrailer(solicitacoes.size());

        arquivo.setHeader(header);
        arquivo.setFila(fila);
        arquivo.setTrailer(trailer);

        return arquivo;
    }


    public void gravarArquivo(Arquivo arquivo){
        String nomeArquivo = "./src/main/resources/minhas-solicitacoes.txt";
        gravaRegistro(nomeArquivo, arquivo.toString());

    }

    private Header gerarHeader(List<Solicitacao> solicitacoes) {
        Header header = new Header();

        Date dataDeHoje = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

        header.setIdPrestador(solicitacoes.get(0).getFkPrestador().getId());
        header.setNomePrestador(solicitacoes.get(0).getFkPrestador().getNome());
        if (solicitacoes.get(0).getFkPrestador().getCnpj() == null) {
            header.setDocumentoPrestador(solicitacoes.get(0).getFkPrestador().getCpf());
        } else {
            header.setDocumentoPrestador(solicitacoes.get(0).getFkPrestador().getCnpj()); }
        header.setDataHoraCriacao(formatter.format(dataDeHoje));
        return header;
    }

    private FilaObj<Body> gerarBody(List<Solicitacao> solicitacoes){

        FilaObj<Body> filaObj = new FilaObj<>(solicitacoes.size());

        for (Solicitacao solicitacao: solicitacoes) {
            Body registro = new Body();
            registro.setIdSolicitante(solicitacao.getFkSolicitante().getId());
            registro.setNomeSolicitante(solicitacao.getFkSolicitante().getNome());
            if(solicitacao.getFkSolicitante().getCnpj() == null){
                registro.setDocumentoSolicitante(solicitacao.getFkSolicitante().getCpf());
            }else {
                registro.setDocumentoSolicitante(solicitacao.getFkSolicitante().getCnpj());
            }
            registro.setDescricaoSolicitacao(solicitacao.getDescricao());
            if(solicitacao.getFkAvaliacao() == null){
                registro.setNota(0.0);
                registro.setAvalicao("");
            } else {
            registro.setNota(solicitacao.getFkAvaliacao().getNota());
            registro.setAvalicao(solicitacao.getFkAvaliacao().getAvaliacao());}
            filaObj.insert(registro);
        }

        return filaObj;

    }

    private Trailer gerarTrailer(Integer qtdRegistros){
        Trailer trailer = new Trailer();
        trailer.setQtdRegistro(qtdRegistros);
        return trailer;
    }



}

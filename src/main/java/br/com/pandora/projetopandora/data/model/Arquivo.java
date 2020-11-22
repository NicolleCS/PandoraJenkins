package br.com.pandora.projetopandora.data.model;

import br.com.pandora.projetopandora.util.FilaObj;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class Arquivo {

    private Header header;
    private FilaObj<Body> fila;
    private Trailer trailer;

    public Arquivo(Header header, FilaObj<Body> fila, Trailer trailer) {
        this.header = header;
        this.fila = fila;
        this.trailer = trailer;
    }

    @Override
    public String toString() {
        return ""+ header.toString() + fila.toString() + trailer.toString();
    }

    public Header getHeader() {
        return header;
    }

    public void setHeader(Header header) {
        this.header = header;
    }

    public FilaObj<Body> getFila() {
        return fila;
    }

    public void setFila(FilaObj<Body> fila) {
        this.fila = fila;
    }

    public Trailer getTrailer() {
        return trailer;
    }

    public void setTrailer(Trailer trailer) {
        this.trailer = trailer;
    }
}

package br.com.pandora.projetopandora.exception;

public class ErroLoginException extends RuntimeException {
    public ErroLoginException(String msg) {
        super(msg);
    }
}

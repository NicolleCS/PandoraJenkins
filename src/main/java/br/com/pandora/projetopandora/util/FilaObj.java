package br.com.pandora.projetopandora.util;

public class FilaObj<T> {

    private Integer tamanho;
    private T[] fila;


    public FilaObj(Integer tamanho) {
        this.tamanho = 0;
        this.fila = (T[]) new Object[tamanho];
    }

    public boolean isEmpty(){
        return this.tamanho == 0;
    }

    public boolean isFull(){
        return this.tamanho == fila.length;
    }

    public void insert(T info){
        if(!isFull()){
            this.fila[this.tamanho] = info;
            this.tamanho++;
        }
        else{
            System.out.println( "Fila cheia");
        }
    }

    public T peek(){
        return this.fila[0];
    }

    public T poll(){
        if(!isEmpty()){
            T primeiro = peek();
            for(int i=1; i < fila.length; i++){
                T novo = fila[i];

                fila[i -1] = novo;

                if (i == fila.length - 1){
                    fila[i] = null;
                }

            }
            tamanho --;
            return primeiro;
        }
        return null;
    }

//    public void exibe() {
//        if(isEmpty()) {
//            System.out.println("Pilha vazia");
//        }
//        else {
//            for(int i = 0; i <= tamanho; i++) {
//                System.out.println(fila[i]);
//            }
//        }
//
//    }

    @Override
    public String toString() {
        return pegaFila();
    }

    public String pegaFila(){
        StringBuilder builder = new StringBuilder();
        while (!isEmpty()){
            builder.append(poll().toString());
        }
        return builder.toString();
    }
}


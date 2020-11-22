package br.com.pandora.projetopandora.data.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "Avaliacoes")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Avaliacao {

    @Id
    @Column(name = "id_avaliacao")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nota")
    private Double nota;

    @Column(name = "texto_avaliacao")
    private String avaliacao;

    public Avaliacao(Double nota, String avaliacao) {
        this.nota = nota;
        this.avaliacao = avaliacao;
    }


}

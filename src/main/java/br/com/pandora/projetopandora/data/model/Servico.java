package br.com.pandora.projetopandora.data.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "Servicos")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Servico {

    @Id
    @Column(name = "id_servico")
    @GeneratedValue(strategy = GenerationType.IDENTITY) //tipo de autoincremento
    private Integer idServico;

    @Column(name = "descricao", length = 200)
    private String descricao;

    @Column(name = "titulo", length = 30)
    private String titulo;

    @Column(name = "imagem")
    private String imagem;

    @ManyToOne
    @JoinColumn(name = "fk_prestador")
    private Prestador fkPrestador;

    @ManyToOne
    @JoinColumn(name = "fk_categoria_servico")
    private CategoriaServico fkCategoriaServico;

}

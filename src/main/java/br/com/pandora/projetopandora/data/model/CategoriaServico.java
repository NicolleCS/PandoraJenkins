package br.com.pandora.projetopandora.data.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "Categorias_Servicos")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoriaServico {

    @Id
    @Column(name = "id_categoria_servico")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idCategoria;

    @Column(name = "nome_servico")
    private String nomeServico;

}

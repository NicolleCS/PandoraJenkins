package br.com.pandora.projetopandora.data.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "Solicitacoes")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter@Setter
public class Solicitacao {

    @Id
    @Column(name = "id_solicitacao")
    @GeneratedValue(strategy = GenerationType.IDENTITY) //tipo de autoincremento
    private int id;

    @Column(name = "status", length = 10)
    private StatusSolicitacaoEnum status;

    @Column(name = "descricao" , length = 150)
    private String descricao;

    // PRECISAMOS CRIAR O TIPO_AVALIADO COM ENUM

    @ManyToOne
    @JoinColumn(name = "fk_solicitante")
    private Solicitante fkSolicitante;

    @ManyToOne
    @JoinColumn(name = "fk_prestador")
    private Prestador fkPrestador;

    @ManyToOne
    @JoinColumn(name = "fk_avaliacao")
    private Avaliacao fkAvaliacao;

}

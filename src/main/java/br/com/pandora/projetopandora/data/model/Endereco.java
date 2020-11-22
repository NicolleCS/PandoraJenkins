package br.com.pandora.projetopandora.data.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "Enderecos")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter@Setter
public class Endereco {

    @Id
    @Column(name = "id_endereco")
    @GeneratedValue(strategy = GenerationType.IDENTITY) //tipo de autoincremento
    private Integer id;

    @Column(name = "logradouro", length = 100)
    private String logradouro;

    @Column(name = "numero", length = 10)
    private String numero;

    @Column(name = "complemento", length = 8)
    private String complemento;

    @Column(name = "cep", length = 8)
    private String cep;

    @Column(name = "bairro" , length = 30)
    private String bairro;

    @Column(name = "uf" , length = 2)
    private String uf;

    @Column(name = "localidade" , length = 35)
    private String localidade;

    @ManyToOne
    @JoinColumn(name = "fk_solicitante")
    private Solicitante fkSolicitante;
}

package br.com.pandora.projetopandora.data.model;

import br.com.pandora.projetopandora.data.dto.UsuarioDto;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "Prestadores")
@Builder
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Prestador extends UsuarioDto {

    @Id
    @Column(name = "id_prestador")
    @GeneratedValue(strategy = GenerationType.IDENTITY) //tipo de autoincremento
    private Integer id;

    @Column(name = "nome_usuario", length = 40)
    private String nome;

    @Column(name = "senha", length = 50)
    private String senha;

    @Column(name = "email", length = 200)
    private String email;

    @Column(name = "cpf", length = 11)
    private String cpf;

    @Column(name = "telefone", length = 15)
    private String telefone;

    @Column(name= "imagem_perfil")
    private String imagem;

    @Column(name = "cnpj", nullable = true)
    private String cnpj;

    @Column(name = "cep", length = 8)
    private String cep;
}

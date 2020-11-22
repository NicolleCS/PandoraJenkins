package br.com.pandora.projetopandora.data.model;

import br.com.pandora.projetopandora.data.dto.UsuarioDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "Solicitantes")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Solicitante extends UsuarioDto {

    @Id
    @Column(name = "id_solicitante")
    @GeneratedValue(strategy = GenerationType.IDENTITY) //tipo de autoincremento
    private Integer id;

    @Column(name = "nome_usuario", length = 40)
    private String nome;

    @Column(name = "senha", length = 20)
    private String senha;

    @Column(name = "email", length = 200)
    private String email;

    @Column(name = "cpf", length = 11)
    private String cpf;

    @Column(name = "telefone", length = 15)
    private String telefone;

    @Column(name = "imagem_perfil")
    private String imagem;

    @Column(name = "cnpj", nullable = true, length = 14)
    private String cnpj;

    private boolean solicitante = true;
}

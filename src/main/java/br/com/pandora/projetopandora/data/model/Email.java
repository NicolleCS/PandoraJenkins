package br.com.pandora.projetopandora.data.model;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Email {
    private String remetente;
    private List<String> destinatario;
    private String assunto;
    private String corpo;

}

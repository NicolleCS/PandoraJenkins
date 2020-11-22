package br.com.pandora.projetopandora.data.model;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmailValidator {
    private String email;
    private boolean mx_found;
    private boolean format_valid;
}

package br.com.pandora.projetopandora.helper;

import java.util.Optional;

public interface CadastroHelper {
    Optional<? extends Object> validarEmail(String email);
    Optional<? extends Object> validarCpf(String cpf);
    Optional<? extends Object> validarCnpj(String cnpj);
}

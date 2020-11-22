package br.com.pandora.projetopandora.helper;

import br.com.pandora.projetopandora.exception.ErroCadastroException;
import br.com.pandora.projetopandora.util.CpfCnpjUtils;

public abstract class UsuarioHelper implements CadastroHelper {

    protected void validarInformacoesCadastro(String email, String cpf, String cnpj) {

        validarInformacoesCadastroSemCnpj(email, cpf);

        if (validarCnpj(cnpj).isPresent()) {
            throw new ErroCadastroException("CNPJ já cadastrado.");
        }

        if (cnpj != null) {
            if (!CpfCnpjUtils.isValid(cnpj)) {
                throw new ErroCadastroException("CNPJ inválido.");
            }
        }
    }

    protected void validarInformacoesCadastroSemCnpj(String email, String cpf) {
        if (validarEmail(email).isPresent()) {
            throw new ErroCadastroException("Email já cadastrado.");
        }
        if (validarCpf(cpf).isPresent()) {
            throw new ErroCadastroException("CPF já cadastrado.");
        }

        if (!CpfCnpjUtils.isValid(cpf)) {
            throw new ErroCadastroException("CPF inválido.");
        }
    }

}

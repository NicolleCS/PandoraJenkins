package br.com.pandora.projetopandora.controller;

import br.com.pandora.projetopandora.data.dto.EmailDto;
import br.com.pandora.projetopandora.data.model.Email;
import br.com.pandora.projetopandora.service.AuxiliarServices;
import br.com.pandora.projetopandora.service.MailerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Random;

@RestController
@RequestMapping("/email")
public class EmailController {

    @Autowired
    private MailerService mailerService;

    @Autowired
    private AuxiliarServices auxiliarServices;

    @PostMapping("/senha")
    public ResponseEntity trocarSenha(@RequestBody EmailDto emailDto) {
        String senha = novaSenha();

        try {
            auxiliarServices.trocarSenha(senha, emailDto.getEmail());
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }

        try {
            String destinatario = String.format("Usuário <%s>", emailDto.getEmail());
            Email email = new Email(
                    "Pandora Team <projetopandora.help@gmail.com>",
                    Arrays.asList(destinatario),
                    "Recuperação de Senha",
                    String.format("Olá! Sua senha Pandora foi redefinida para: %s \n" +
                            " não se esqueça: sua senha não deve ser compartilhada! \n" +
                            "Abraços de toda a equipe Pandora :)", senha)
            );
            mailerService.enviar(email);
            return ResponseEntity.ok().build();
        } catch (Exception exception) {
            return ResponseEntity.badRequest().body(exception.getMessage());
        }

    }

//    @PostMapping("/report")
//    public ResponseEntity reportarUsuario(@RequestBody EmailDto reportarUsuario) {
//
//    }


    private String novaSenha() {
        Random r = new Random();
        byte[] bytes = new byte[5];
        r.nextBytes(bytes);
        String novaSenha = "";
        for (byte b : bytes) {
            novaSenha += b;
        }
        return novaSenha;
    }


}

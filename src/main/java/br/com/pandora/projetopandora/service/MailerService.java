package br.com.pandora.projetopandora.service;

import br.com.pandora.projetopandora.data.model.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class MailerService {

    @Autowired
    private JavaMailSender javaMailSender;

    public void enviar(Email mensagem){
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom(mensagem.getRemetente());
        simpleMailMessage.setTo(mensagem.getDestinatario()
                .toArray(new String[mensagem.getDestinatario().size()]));
        simpleMailMessage.setSubject(mensagem.getAssunto());
        simpleMailMessage.setText(mensagem.getCorpo());

        javaMailSender.send(simpleMailMessage);
    }
}

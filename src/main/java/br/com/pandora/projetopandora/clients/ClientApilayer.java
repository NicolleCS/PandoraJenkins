package br.com.pandora.projetopandora.clients;

import br.com.pandora.projetopandora.data.model.EmailValidator;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value="apilayer", url="http://apilayer.net/api")
public interface ClientApilayer {

    @GetMapping("/check")
    public EmailValidator validarEmail(@RequestParam String access_key,
                                       @RequestParam String email,
                                       @RequestParam int smtp,
                                       @RequestParam int format);
}

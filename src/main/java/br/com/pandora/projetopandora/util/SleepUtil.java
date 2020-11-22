package br.com.pandora.projetopandora.util;

import org.springframework.stereotype.Component;

@Component
public class SleepUtil {

    public void sleep() throws InterruptedException {
        Thread.sleep(1000);
    }
}

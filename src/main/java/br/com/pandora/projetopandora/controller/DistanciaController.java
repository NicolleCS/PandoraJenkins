package br.com.pandora.projetopandora.controller;

import br.com.pandora.projetopandora.data.vo.DistanceVO;
import br.com.pandora.projetopandora.data.vo.ResponseDistanceVO;
import br.com.pandora.projetopandora.data.vo.filter.CepFilterVO;
import br.com.pandora.projetopandora.service.DistanciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/distancias")
public class DistanciaController {

    @Autowired
    private DistanciaService service;

    @GetMapping
    public ResponseDistanceVO getDistancia(CepFilterVO filter) {
        return service.getDistance(filter);
    }
}

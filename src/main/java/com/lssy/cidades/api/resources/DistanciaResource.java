package com.lssy.cidades.api.resources;


import com.lssy.cidades.api.enums.RaioDaTerra;
import com.lssy.cidades.api.services.DistanciaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/distancia")
public class DistanciaResource {

    private final DistanciaService distanciaService;
    Logger log = LoggerFactory.getLogger(DistanciaResource.class);

    public DistanciaResource(DistanciaService service) {
        this.distanciaService = service;
    }

    @GetMapping("/porPontos")
    public Double porPontos(@RequestParam(name = "de") final Long cidade1,
                           @RequestParam(name = "para") final Long cidade2) {
        log.info("porPontos");
        return distanciaService.distanciaPorPontosEmMilhas(cidade1, cidade2);
    }

    @GetMapping("/porCubo")
    public Double porCubo(@RequestParam(name = "de") final Long cidade1,
                         @RequestParam(name = "para") final Long cidade2) {
        log.info("porCubo");
        return distanciaService.distanciaPorCubosEmMetros(cidade1, cidade2);
    }

    @GetMapping("/porMatematica")
    public Double porMatematica(@RequestParam(name = "de") final Long cidade1,
                         @RequestParam(name = "para") final Long cidade2,
                         @RequestParam final RaioDaTerra unidade) {
        log.info("byMath");
        return distanciaService.distanceUsandoMatematica(cidade1, cidade2, unidade);
    }
}

package com.lssy.cidades.api.services;


import com.lssy.cidades.api.entities.Cidade;
import com.lssy.cidades.api.enums.RaioDaTerra;
import com.lssy.cidades.api.repositories.CidadeRepository;
import com.lssy.cidades.api.utils.StringLocationUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.geo.Point;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

import static java.lang.Math.*;

@Service
public class DistanciaService {
    private final CidadeRepository cidadeRepository;
    Logger log = LoggerFactory.getLogger(DistanciaService.class);

    public DistanciaService(final CidadeRepository cidadeRepository) {
        this.cidadeRepository = cidadeRepository;
    }

    /**
     * 1st option
     *
     * @param cidade1
     * @param cidade2
     * @param unidade
     * @return
     */
    public Double distanceUsandoMatematica(final Long cidade1, final Long cidade2, final RaioDaTerra unidade) {
        log.info("distanceUsandoMatematica({}, {}, {})", cidade1, cidade2, unidade);
        final List<Cidade> cities = cidadeRepository.findAllById((Arrays.asList(cidade1, cidade2)));

        final Double[] location1 = StringLocationUtils.transform(cities.get(0).getGeoLocalizacao());
        final Double[] location2 = StringLocationUtils.transform(cities.get(1).getGeoLocalizacao());

        return doCalculation(location1[0], location1[1], location2[0], location2[1], unidade);
    }

    /**
     * 2nd option
     *
     * @param cidade1
     * @param cidade2
     * @return
     */
    public Double distanciaPorPontosEmMilhas(final Long cidade1, final Long cidade2) {
        log.info("distanciaPorPontosEmMilhas({}, {})", cidade1, cidade2);
        return cidadeRepository.distanciaPorPontos(cidade1, cidade2);
    }

    /**
     * 3rd option
     *
     * @param cidade1
     * @param cidade2
     * @param unidade
     * @return
     */
    public Double DistanciaUsandoPontos(final Long cidade1, final Long cidade2, final RaioDaTerra unidade) {
        log.info("DistanciaUsandoPontos({}, {}, {})", cidade1, cidade2, unidade);
        final List<Cidade> cities = cidadeRepository.findAllById((Arrays.asList(cidade1, cidade2)));

        Point p1 = cities.get(0).getCoordenadas();
        Point p2 = cities.get(1).getCoordenadas();

        return doCalculation(p1.getX(), p1.getY(), p2.getX(), p2.getY(), unidade);
    }

    /**
     * 4th option
     *
     * @param cidade1
     * @param cidade2
     * @return
     */
    public Double distanciaPorCubosEmMetros(Long cidade1, Long cidade2) {
        log.info("distanciaPorCubosEmMetros({}, {})", cidade1, cidade2);
        final List<Cidade> cities = cidadeRepository.findAllById((Arrays.asList(cidade1, cidade2)));

        Point p1 = cities.get(0).getCoordenadas();
        Point p2 = cities.get(1).getCoordenadas();

        return cidadeRepository.distanciaPorCubo(p1.getX(), p1.getY(), p2.getX(), p2.getY());
    }

    private double doCalculation(final double lat1, final double lon1, final double lat2,
                                 final double lon2, final RaioDaTerra raioDaTerra) {
        double lat = toRadians(lat2 - lat1);
        double lon = toRadians(lon2 - lon1);
        double a = sin(lat / 2) * sin(lat / 2) +
                cos(toRadians(lat1)) * cos(toRadians(lat2)) * sin(lon / 2) * sin(lon / 2);
        double c = 2 * atan2(sqrt(a), sqrt(1 - a));

        return raioDaTerra.getValor() * c;
    }
}

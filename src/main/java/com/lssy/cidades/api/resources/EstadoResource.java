package com.lssy.cidades.api.resources;

import com.lssy.cidades.api.entities.Estado;
import com.lssy.cidades.api.services.EstadoService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;


@RestController
@RequestMapping("/estados")
public class EstadoResource {
    private final EstadoService estadoService;

    public EstadoResource(EstadoService estadoService) {
        this.estadoService = estadoService;
    }

    @GetMapping
    public Page<Estado> estados(Pageable pageable){
        return estadoService.listarEstados(pageable);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Estado> estado(@PathVariable Long id){
        Optional<Estado> estado = estadoService.buscarEstado(id);
        return estado.map(value -> ResponseEntity.ok().body(value)).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
package com.lssy.cidades.api.resources;

import com.lssy.cidades.api.entities.Pais;
import com.lssy.cidades.api.services.PaisService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController
@RequestMapping("/paises")
public class PaisResources {
    private final PaisService paisService;

    public PaisResources(PaisService paisService) {
        this.paisService = paisService;
    }

    @GetMapping
    public Page<Pais> paises(Pageable pageable){
        return paisService.listarPaises(pageable);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pais> pais(@PathVariable Long id){
        Optional<Pais> pais = paisService.buscarPais(id);
        return pais.map(value -> ResponseEntity.ok().body(value)).orElseGet(() -> ResponseEntity.notFound().build());
    }
}

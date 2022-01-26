package com.lssy.cidades.api.resources;

import com.lssy.cidades.api.entities.Cidade;
import com.lssy.cidades.api.services.CidadeService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;


@RestController
@RequestMapping("/cidades")
public class CidadeResource {
    private final CidadeService cidadeService;

    public CidadeResource(CidadeService cidadeService) {
        this.cidadeService = cidadeService;
    }

    @GetMapping
    public Page<Cidade> cidades(Pageable pageable){
        return cidadeService.listarCidades(pageable);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cidade> cidade(@PathVariable Long id){
        Optional<Cidade> cidade = cidadeService.buscarCidade(id);
        return cidade.map(value -> ResponseEntity.ok().body(value)).orElseGet(() -> ResponseEntity.notFound().build());
    }
}

package com.lssy.cidades.api.services;

import com.lssy.cidades.api.entities.Pais;
import com.lssy.cidades.api.repositories.PaisRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PaisService {
    private final PaisRepository paisRepository;

    public PaisService(PaisRepository paisRepository) {
        this.paisRepository = paisRepository;
    }

    public Page<Pais> listarPaises(Pageable pageable){
        return paisRepository.findAll(pageable);
    }

    public Optional<Pais> buscarPais(Long id){
        return paisRepository.findById(id);
    }
}

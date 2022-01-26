package com.lssy.cidades.api.services;

import com.lssy.cidades.api.entities.Estado;
import com.lssy.cidades.api.repositories.EstadoRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EstadoService {
    private final EstadoRepository estadoRepository;

    public EstadoService(EstadoRepository estadoRepository) {
        this.estadoRepository = estadoRepository;
    }

    public Page<Estado> listarEstados(Pageable pageable){
        return estadoRepository.findAll(pageable);
    }

    public Optional<Estado> buscarEstado(Long id){
        return estadoRepository.findById(id);
    }
}

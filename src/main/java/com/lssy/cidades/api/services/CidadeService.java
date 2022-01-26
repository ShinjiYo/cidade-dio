package com.lssy.cidades.api.services;

import com.lssy.cidades.api.entities.Cidade;
import com.lssy.cidades.api.entities.Pais;
import com.lssy.cidades.api.repositories.CidadeRepository;
import com.lssy.cidades.api.repositories.PaisRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CidadeService {
    private final CidadeRepository cidadeRepository;

    public CidadeService(CidadeRepository cidadeRepository) {
        this.cidadeRepository = cidadeRepository;
    }

    public Page<Cidade> listarCidades(Pageable pageable){
        return cidadeRepository.findAll(pageable);
    }

    public Optional<Cidade> buscarCidade(Long id){
        return cidadeRepository.findById(id);
    }
}

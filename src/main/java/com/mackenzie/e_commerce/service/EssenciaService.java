package com.mackenzie.e_commerce.service;

import com.mackenzie.e_commerce.dto.EssenciaDTO;
import com.mackenzie.e_commerce.model.Essencia;
import com.mackenzie.e_commerce.repository.EssenciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EssenciaService {

    @Autowired
    private EssenciaRepository repository;

    public List<Essencia> listarTodas() {
        return repository.findAll(Sort.by(Sort.Direction.ASC, "nome"));
    }

    public Essencia criar(EssenciaDTO dto) {
        Essencia essencia = new Essencia();
        essencia.setNome(dto.nome());
        return repository.save(essencia);
    }

    public void deletar(Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Essência não encontrada com ID: " + id);
        }
        repository.deleteById(id);
    }
}
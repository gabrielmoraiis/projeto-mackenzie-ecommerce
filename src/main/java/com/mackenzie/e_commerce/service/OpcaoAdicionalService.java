package com.mackenzie.e_commerce.service;

import com.mackenzie.e_commerce.dto.OpcaoAdicionalDTO;
import com.mackenzie.e_commerce.model.OpcaoAdicional;
import com.mackenzie.e_commerce.repository.OpcaoAdicionalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OpcaoAdicionalService {

    @Autowired
    private OpcaoAdicionalRepository repository;

    public OpcaoAdicional criar(OpcaoAdicionalDTO dto) {
        OpcaoAdicional opcao = new OpcaoAdicional();
        opcao.setNome(dto.nome());
        opcao.setPrecoAdicional(dto.precoAdicional());
        return repository.save(opcao);
    }

    public List<OpcaoAdicional> listarTodas() {
        return repository.findAll();
    }

    public void deletar(Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Essência não encontrada com ID: " + id);
        }
        repository.deleteById(id);
    }
}
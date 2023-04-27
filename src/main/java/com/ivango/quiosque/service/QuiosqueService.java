package com.ivango.quiosque.service;

import com.ivango.quiosque.entity.Quiosque;
import com.ivango.quiosque.repository.QuiosqueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuiosqueService {

    @Autowired
    private QuiosqueRepository quiosqueRepository;

    public Quiosque salvarQuiosque(Quiosque quiosque) {
        return quiosqueRepository.save(quiosque);
    }

    public List<Quiosque> buscarQuiosques() {
        return quiosqueRepository.findAll();
    }

    public Quiosque atualizarQuiosque(Quiosque quiosque) {
        Quiosque existeQuiosque = quiosqueRepository.findById(quiosque.getId()).orElse(null);
        existeQuiosque.setNome(quiosque.getNome());
        existeQuiosque.setEndereco(quiosque.getEndereco());
        existeQuiosque.setCidade(quiosque.getCidade());
        return quiosqueRepository.save(existeQuiosque);
    }

    public String removerQuiosque(Long id) {
        quiosqueRepository.deleteById(id);
        return "Quiosque removido com sucesso! -> " + id;
    }
}

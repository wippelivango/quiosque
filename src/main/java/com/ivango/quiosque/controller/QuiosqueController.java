package com.ivango.quiosque.controller;

import com.ivango.quiosque.entity.Quiosque;
import com.ivango.quiosque.service.QuiosqueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class QuiosqueController {

    @Autowired
    private QuiosqueService quiosqueService;
/*
    @GetMapping("/quiosque")
    public String listar() {
        return "Hello World!";
    }
*/
    @GetMapping("/buscar")
    public List<Quiosque> listarQuiosques() {
        return quiosqueService.buscarQuiosques();
    }

    @PostMapping("/salvar")
    public Quiosque adicionarQuiosque(@RequestBody Quiosque quiosque) {
        return quiosqueService.salvarQuiosque(quiosque);
    }

    @PutMapping("/atualizar")
    public Quiosque atualizarQuiosque(@RequestBody Quiosque quiosque) {
        return quiosqueService.atualizarQuiosque(quiosque);
    }

    @DeleteMapping("/remover/{id}")
    public String removerQuiosque(@PathVariable Long id) {
        return quiosqueService.removerQuiosque(id);
    }
}

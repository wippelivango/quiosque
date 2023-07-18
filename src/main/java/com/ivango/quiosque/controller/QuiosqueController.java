package com.ivango.quiosque.controller;

import com.ivango.quiosque.entity.Quiosque;
import com.ivango.quiosque.service.QuiosqueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

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

    final String urlFrontend = "http://localhost:3000";

    @CrossOrigin(origins = urlFrontend)
    @GetMapping("/quiosque")
    public List<Quiosque> listarQuiosques(@RequestParam("nome_like") String nome) {
        return quiosqueService.buscarQuiosques(nome);
    }

    @CrossOrigin(origins = urlFrontend)
    @GetMapping("/quiosque/contar")
    public Long contarQuiosques() {
        return quiosqueService.contarQuiosques();
    }

    @CrossOrigin(origins = urlFrontend)
    @GetMapping("/quiosque/{id}")
    public Optional<Quiosque> listarQuiosques(@PathVariable Long id) {
        return quiosqueService.buscarQuiosque(id);
    }

    @CrossOrigin(origins = urlFrontend)
    @PostMapping("/quiosque")
    public Quiosque adicionarQuiosque(@RequestBody Quiosque quiosque) {
        return quiosqueService.salvarQuiosque(quiosque);
    }

    @CrossOrigin(origins = urlFrontend)
    @PutMapping("/quiosque/{id}")
    public Quiosque atualizarQuiosque(@RequestBody Quiosque quiosque) {
        return quiosqueService.atualizarQuiosque(quiosque);
    }

    @CrossOrigin(origins = urlFrontend)
    @DeleteMapping("/quiosque/{id}")
    public String removerQuiosque(@PathVariable Long id) {
        return quiosqueService.removerQuiosque(id);
    }
}

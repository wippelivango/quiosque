package com.ivango.quiosque.controllers;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class QuiosqueController {

    @GetMapping("/quiosque")
    public String listar() {
        return "Hello World!";
    }

    @PostMapping("/quiosque")
    public String adicionar() {
        return "Adicionar";
    }

    @PutMapping("/quiosque")
    public String alterar() {
        return "Alterar";
    }

    @DeleteMapping("/quiosque")
    public String remover() {
        return "Remover";
    }
}

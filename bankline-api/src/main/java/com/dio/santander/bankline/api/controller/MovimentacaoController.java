package com.dio.santander.bankline.api.controller;

import com.dio.santander.bankline.api.dto.MovimentacaoDTO;
import com.dio.santander.bankline.api.model.Movimentacao;
import com.dio.santander.bankline.api.repository.MovimentacaoRepository;
import com.dio.santander.bankline.api.service.MovimentacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movimentacao")
public class MovimentacaoController {
    @Autowired
    private MovimentacaoRepository repository;

    @Autowired
    private MovimentacaoService service;

    @GetMapping
    public List<Movimentacao> findAll() {
        return repository.findAll();
    }

//    @GetMapping("/{idConta}")
//    public List<Movimentacao> findAll(@PathVariable("idConta") Integer idConta) {
//        return repository.findByIdConta(idConta);
//    }

    @PostMapping
    public void save(@RequestBody MovimentacaoDTO dto) {
        service.save(dto);
    }
}

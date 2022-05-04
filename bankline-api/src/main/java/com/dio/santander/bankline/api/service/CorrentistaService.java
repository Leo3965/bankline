package com.dio.santander.bankline.api.service;

import com.dio.santander.bankline.api.dto.CorrentistaDTO;
import com.dio.santander.bankline.api.model.Conta;
import com.dio.santander.bankline.api.model.Correntista;
import com.dio.santander.bankline.api.repository.CorrentistaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class CorrentistaService {

    @Autowired
    private CorrentistaRepository repository;

    public void save(CorrentistaDTO dto) {
        var conta = new Conta();
        conta.setSaldo(0.0);
        conta.setNumero(new Date().getTime());

        var correntista = new Correntista();
        correntista.setCpf(dto.getCpf());
        correntista.setNome(dto.getNome());

        correntista.setConta(conta);

        repository.save(correntista);
    }
}

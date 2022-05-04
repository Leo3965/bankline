package com.dio.santander.bankline.api.service;

import com.dio.santander.bankline.api.dto.MovimentacaoDTO;
import com.dio.santander.bankline.api.model.Movimentacao;
import com.dio.santander.bankline.api.model.MovimentacaoTipo;
import com.dio.santander.bankline.api.repository.CorrentistaRepository;
import com.dio.santander.bankline.api.repository.MovimentacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class MovimentacaoService {
    @Autowired
    private MovimentacaoRepository repository;

    @Autowired
    private CorrentistaRepository correntistaRepository;

    public void save(MovimentacaoDTO dto) {
        var valor = dto.getTipo() == MovimentacaoTipo.RECEITA ?
                dto.getValor() : (dto.getValor() * -1);

        var movimentacao = new Movimentacao();
        movimentacao.setDataHora(LocalDateTime.now());
        movimentacao.setDescricao(dto.getDescricao());
        movimentacao.setIdConta(dto.getIdConta());
        movimentacao.setTipo(dto.getTipo());
        movimentacao.setValor(valor);

        var correntista = correntistaRepository.findById(dto.getIdConta()).orElse(null);
        if(correntista != null) {
            correntista.getConta().setSaldo(correntista.getConta().getSaldo() + valor);
            correntistaRepository.save(correntista);
        }
        repository.save(movimentacao);
    }
}

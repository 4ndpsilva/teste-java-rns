package com.rns.testes.java.seeder;

import java.util.stream.LongStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.rns.testes.java.model.Produto;
import com.rns.testes.java.service.IProdutoService;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class ProdutoSeeder {

    @Autowired
    IProdutoService service;

    @EventListener
    public void seedFilial(ContextRefreshedEvent event) {
        try {
            log.info("Criando produtos....");
            criarProdutos();
        } 
        catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    private void criarProdutos() {
    	LongStream
    		.range(1, 26)
    		.forEach(i -> service.save(new Produto("Cod-Produto-"+i, "Produto " +i)));
    }
}
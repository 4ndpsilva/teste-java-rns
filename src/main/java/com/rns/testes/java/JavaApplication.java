package com.rns.testes.java;

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.rns.testes.java.model.Estocagem;
import com.rns.testes.java.model.Filial;
import com.rns.testes.java.model.Produto;
import com.rns.testes.java.service.IFilialService;
import com.rns.testes.java.service.IProdutoService;
import com.rns.testes.java.service.impl.EstocagemService;

@SpringBootApplication
public class JavaApplication implements CommandLineRunner{

	@Autowired
    EstocagemService service;
	
	@Autowired
    IFilialService filialService;
	
	@Autowired
    IProdutoService produtoService;
	
    public static void main(String[] args) {
        SpringApplication.run(JavaApplication.class, args);
    }
    
    @Override
    public void run(String... args) throws Exception {
    	criarEstoque();
    }
    
    private void criarEstoque() {
		List<Produto> produtos = produtoService.findAll();
		Filial f1 = filialService.findById(1L);
		
		for(Produto p : produtos) {
			Estocagem estoque = new Estocagem();
			estoque.setFilial(f1);
			estoque.setProduto(p);
			estoque.setQuantidade(new Random().nextInt(100) + 1);
			service.save(estoque);
		}
	}
}
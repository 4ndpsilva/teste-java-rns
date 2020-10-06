package com.rns.testes.java.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rns.testes.java.dao.IEstocagemDAO;
import com.rns.testes.java.exception.FilialAndOrProdutoNotFoundException;
import com.rns.testes.java.model.Estocagem;
import com.rns.testes.java.model.Filial;
import com.rns.testes.java.model.Produto;
import com.rns.testes.java.service.AbstractGenericServicePersistence;
import com.rns.testes.java.service.IEstocagemService;
import com.rns.testes.java.service.IFilialService;
import com.rns.testes.java.service.IProdutoService;

@Service
public class EstocagemService extends AbstractGenericServicePersistence<IEstocagemDAO, Estocagem, Long> implements IEstocagemService{
	@Autowired
	private IFilialService filialService;
	
	@Autowired
	private IProdutoService produtoService;
	
	@Override
	public Estocagem save(Estocagem t) {
		this.populate(t);
		return super.save(t);
	}
	
	@Override
	public Estocagem update(Long id, Estocagem t) {
		this.populate(t);
		t.setId(id);
		return super.update(id, t);
	}
	
	private void populate(Estocagem t) {
		Filial filial = filialService.findById(t.getFilial().getId());
		Produto produto = produtoService.findById(t.getProduto().getId());
		t.setFilial(filial);
		t.setProduto(produto);
	}
	
	public List<Estocagem> findByFilial(Long filialId) {
		return dao.findByFilialId(filialId);
	}
	
	public List<Estocagem> findByProduto(String produtoId) {
		return dao.findByProdutoId(produtoId);
	}
	
	public Estocagem findByFilialAndProduto(Long filialId, String produtoId) {
		Optional<Estocagem> opEstocagem = dao.findByFilialIdAndProdutoId(filialId, produtoId);
		
		if(opEstocagem.isPresent()) {
			return opEstocagem.get();
		}
		
		throw new FilialAndOrProdutoNotFoundException();
	}
	
	public Estocagem transferir(Long filialOrigemId, Long filialDestinoId, String produtoId) {
		Estocagem estocagem = findByFilialAndProduto(filialOrigemId, produtoId);
		Filial filialDestino = filialService.findById(filialDestinoId);
		estocagem.setFilial(filialDestino);
		return super.update(estocagem.getId(), estocagem);
	}
}
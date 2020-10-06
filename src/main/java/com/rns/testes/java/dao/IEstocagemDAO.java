package com.rns.testes.java.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rns.testes.java.model.Estocagem;

/**
 * Interface representa a camada de persistência da entidade Estocagem. Deve ser injetada <b>exclusivamente</b> em uma
 * camada service.
 */
public interface IEstocagemDAO extends JpaRepository<Estocagem, Long> {
	public List<Estocagem> findByFilialId(Long filialId);
	
	public List<Estocagem> findByProdutoId(String produtoId);
	
	public Optional<Estocagem> findByFilialIdAndProdutoId(Long filialId, String produtoId);
}
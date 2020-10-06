package com.rns.testes.java.mapper;

import com.rns.testes.java.dto.ProdutoDTO;
import com.rns.testes.java.model.Produto;

public class ProdutoMapper implements IEntityMapper<Produto, ProdutoDTO, String> {
	@Override
	public Produto toEntity(ProdutoDTO dto) {
		return new Produto(dto.getId(), dto.getNome());
	}
}
package com.rns.testes.java.mapper;

import com.rns.testes.java.dto.EstocagemDTO;
import com.rns.testes.java.model.Estocagem;
import com.rns.testes.java.model.Filial;
import com.rns.testes.java.model.Produto;

public class EstocagemMapper implements IEntityMapper<Estocagem, EstocagemDTO, Long>{
	@Override
	public Estocagem toEntity(EstocagemDTO dto) {
		Filial filial = new Filial();
		filial.setId(dto.getFilialId());
		Produto produto = new Produto();
		produto.setId(dto.getProdutoId());
		return new Estocagem(filial, produto, dto.getQuantidade());
	}
}
package com.rns.testes.java.mapper;

import com.rns.testes.java.dto.FilialDTO;
import com.rns.testes.java.model.Filial;

public class FilialMapper implements IEntityMapper<Filial, FilialDTO, Long>{
	@Override
	public Filial toEntity(FilialDTO dto) {
		Filial filial = new Filial();
		filial.setId(dto.getId());
		filial.setCnpj(dto.getCnpj());
		filial.setRazaoSocial(dto.getRazaoSocial());
		filial.setEndereco(dto.getEndereco());
		filial.setTipoFilial(dto.getTipoFilial());
		return filial;
	}
}
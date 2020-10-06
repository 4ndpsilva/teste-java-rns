package com.rns.testes.java.mapper;

import java.io.Serializable;

import com.rns.testes.java.dto.GenericDTO;
import com.rns.testes.java.model.GenericEntity;

public interface IEntityMapper<T extends GenericEntity<ID>, D extends GenericDTO<ID>, ID extends Serializable> {
	public T toEntity(D dto);
}
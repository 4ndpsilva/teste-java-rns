package com.rns.testes.java.dto;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class GenericDTO<ID extends Serializable> {
	private ID id;
}
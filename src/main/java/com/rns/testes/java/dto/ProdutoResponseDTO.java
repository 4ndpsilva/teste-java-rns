package com.rns.testes.java.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProdutoResponseDTO {
	private String id;
	private String nome;
	private String filial;
	private String tipoFilial;
	private String local;
	private Integer quantidade;
}
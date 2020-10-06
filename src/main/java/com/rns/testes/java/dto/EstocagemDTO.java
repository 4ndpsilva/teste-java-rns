package com.rns.testes.java.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EstocagemDTO extends GenericDTO<Long> {
	@NotNull(message = "Obrigatório informar a filial")
	private Long filialId;
	
	@NotBlank(message = "Obrigatório informar o produto")
	private String produtoId;
	
	@NotNull(message = "Obrigatório informar a quantidade disponível")
	@Positive(message = "Informar um valor inteiro maior que zero")
	private Integer quantidade;
}
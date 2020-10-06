package com.rns.testes.java.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EstocagemDTO extends GenericDTO<Long> {
	@NotNull(message = "Obrigat�rio informar a filial")
	private Long filialId;
	
	@NotBlank(message = "Obrigat�rio informar o produto")
	private String produtoId;
	
	@NotNull(message = "Obrigat�rio informar a quantidade dispon�vel")
	@Positive(message = "Informar um valor inteiro maior que zero")
	private Integer quantidade;
}
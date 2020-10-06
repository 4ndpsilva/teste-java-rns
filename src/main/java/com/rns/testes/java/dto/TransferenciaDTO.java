package com.rns.testes.java.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TransferenciaDTO {
	@NotNull(message = "Obrigat�rio informar a filial de origem")
	private Long filialOrigemId;
	
	@NotNull(message = "Obrigat�rio informar a filial de destino")
	private Long filialDestinoId;
	
	@NotBlank(message = "Obrigat�rio informar o produto a ser transferido")
	private String produtoId;
}
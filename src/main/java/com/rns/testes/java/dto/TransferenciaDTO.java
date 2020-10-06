package com.rns.testes.java.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TransferenciaDTO {
	@NotNull(message = "Obrigatório informar a filial de origem")
	private Long filialOrigemId;
	
	@NotNull(message = "Obrigatório informar a filial de destino")
	private Long filialDestinoId;
	
	@NotBlank(message = "Obrigatório informar o produto a ser transferido")
	private String produtoId;
}
package com.rns.testes.java.dto;

import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProdutoDTO extends GenericDTO<String>{
	@NotBlank(message = "O nome do produto é obrigatório")
    private String nome;
}
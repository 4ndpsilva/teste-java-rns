package com.rns.testes.java.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.br.CNPJ;

import com.rns.testes.java.enums.EnumTipoFilial;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FilialDTO extends GenericDTO<Long>{
	@NotBlank(message = "A raz�o social � obrigat�ria")
    private String razaoSocial;
    
	@NotBlank(message = "O CNPJ � obrigat�rio")
    @CNPJ(message = "O CNPJ � inv�lido")
    private String cnpj;
    
    @NotBlank(message = "O endere�o � obrigat�rio")
    private String endereco;
    
    @NotNull(message = "O tipo da filial � obrigat�rio")
    private EnumTipoFilial tipoFilial;
}
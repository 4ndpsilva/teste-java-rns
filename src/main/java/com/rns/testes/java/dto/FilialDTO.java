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
	@NotBlank(message = "A razão social é obrigatória")
    private String razaoSocial;
    
	@NotBlank(message = "O CNPJ é obrigatório")
    @CNPJ(message = "O CNPJ é inválido")
    private String cnpj;
    
    @NotBlank(message = "O endereço é obrigatório")
    private String endereco;
    
    @NotNull(message = "O tipo da filial é obrigatório")
    private EnumTipoFilial tipoFilial;
}
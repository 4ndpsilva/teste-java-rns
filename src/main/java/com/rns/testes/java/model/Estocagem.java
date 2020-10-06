package com.rns.testes.java.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "TB_ESTOCAGEM")
@SequenceGenerator(name = "ESTOCAGEM_SEQ", sequenceName = "ESTOCAGEM_SEQ", allocationSize = 1)
@SuppressWarnings("serial")
public class Estocagem extends GenericEntity<Long>{
	@Id
	@GeneratedValue(generator = "ESTOCAGEM_SEQ", strategy = GenerationType.SEQUENCE)
	private Long id;
	
	@ManyToOne
	private Filial filial;
	
	@ManyToOne
	private Produto produto;
	
	private Integer quantidade;
	
	public Estocagem(Filial filial, Produto produto, Integer quantidade) {
		this.filial = filial;
		this.produto = produto;
		this.quantidade = quantidade;
	}
}
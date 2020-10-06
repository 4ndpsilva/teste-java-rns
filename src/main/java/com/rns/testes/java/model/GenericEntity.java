package com.rns.testes.java.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.domain.Persistable;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

/**
 * Esta entidade representa o escopo mínimo de uma entidade, portanto toda classe entidade deve herda-la.
 */
@Getter
@Setter
@MappedSuperclass
@SuppressWarnings("serial")
public abstract class GenericEntity<T extends Serializable> implements Serializable, Persistable<T> {
	/**
     * Atributo auto-gerado em cada operaçãoo realizada pela DAO. Indica quantas vezes o objeto foi manipulado.
     */
	@JsonIgnore
    @Version
    @Column(name = "VERSAO", unique = false, nullable = false, precision = 10)
    protected Long versao;

    /**
     * Atributo auto-gerado em cada opera??o realizada pela DAO.
     */
    @JsonIgnore
    @UpdateTimestamp
    @Column(name = "DATA_ULT_ALTERACAO", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    protected LocalDateTime dataUltAlteracao;

    @JsonIgnore
    public boolean isNew() {
        return getId() == null;
    }
}
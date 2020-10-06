package com.rns.testes.java.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rns.testes.java.model.Filial;

/**
 * Interface representa a camada de persist�ncia da entidade Filial. Deve ser injetada <b>exclusivamente</b> em uma
 * camada service.
 */
public interface IFilialDAO extends JpaRepository<Filial, Long> {}
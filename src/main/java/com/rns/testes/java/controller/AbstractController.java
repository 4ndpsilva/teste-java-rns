package com.rns.testes.java.controller;

import java.io.Serializable;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.rns.testes.java.dto.GenericDTO;
import com.rns.testes.java.mapper.IEntityMapper;
import com.rns.testes.java.model.GenericEntity;
import com.rns.testes.java.service.IGenericService;

public abstract class AbstractController<T extends GenericEntity<ID>, D extends GenericDTO<ID>, ID extends Serializable> {
	@Autowired
    IGenericService<T, ID> service;
	
	private IEntityMapper<T, D, ID> mapper;
	
	public AbstractController() {}
	
	public AbstractController(IEntityMapper<T, D, ID> mapper) {
		this.mapper = mapper;
	}
	
    @GetMapping
    public ResponseEntity<List<T>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<T> findById(@PathVariable ID id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<T> update(@PathVariable ID id, @RequestBody @Valid D dto) {
    	T entity = mapper.toEntity(dto);
        return ResponseEntity.ok(service.update(id, entity));
    }

    @PostMapping
    public ResponseEntity<T> insert(@RequestBody @Valid D dto) {
    	T entity = mapper.toEntity(dto);
        return ResponseEntity.ok(service.save(entity));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable ID id) {
    	service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
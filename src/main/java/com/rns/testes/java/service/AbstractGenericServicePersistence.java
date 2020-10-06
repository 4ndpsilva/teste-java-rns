package com.rns.testes.java.service;


import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.rns.testes.java.exception.ConflictIdException;
import com.rns.testes.java.exception.ResourceNotFoundException;
import com.rns.testes.java.model.GenericEntity;

/**
 * Todas as Services que manipulam entidades persistidas devem herdar essa classe.
 * AquID estamos implementando métodos básicos para adição, update e delete físico.
 * Se necessário sobrescreva os métodos na sua service.
 *
 * @param <T> entidade
 * @param <I> classe do id da entidade
 */
@Service
public abstract class AbstractGenericServicePersistence<D extends JpaRepository<T, ID>, T extends GenericEntity<ID>,
        ID extends Serializable> implements IGenericService<T, ID> {

    @Autowired
    protected D dao;

    @Override
    public T save(T t) {
    	if (t.getId() != null) {    		
    		if(dao.existsById(t.getId())) {
    			throw new ConflictIdException();
    		}
    	}
    	
        return dao.save(t);
    }

    @Override
    public T update(ID id, T t) {
        if (id != null) {
            Optional<T> old = dao.findById(id);
            
            if (old.isPresent()) {
                t.setVersao(old.get().getVersao());
                return dao.save(t);
            } 
             
            throw new ResourceNotFoundException();
        } 
        
        throw new UnsupportedOperationException();
    }

    @Override
    public void delete(ID i) {
    	T t = this.findById(i);
        dao.deleteById(t.getId());
    }

    @Override
    public List<T> findAll() {
        return dao.findAll();
    }

    @Override
    public T findById(ID i) {
        Optional<T> t = dao.findById(i);
        
        if (t.isPresent()) {
        	return t.get();
        }
        
        throw new ResourceNotFoundException();
    }
}
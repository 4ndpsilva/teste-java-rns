package com.rns.testes.java.service;

import java.io.Serializable;
import java.util.List;

import com.rns.testes.java.model.GenericEntity;

/**
 * Interface representa os m?todos m?nimos que devem ser criados nas services dessa API.
 *
 * @param <T> Entidade que a service ser? respons?vel.
 * @param <I> A classe do id da entidade.
 */
public interface IGenericService<T extends GenericEntity<ID>, ID extends Serializable> {

    public T save(T t);

    public T update(ID id, T t);

    public void delete(ID i);
    
    public List<T> findAll();

    public T findById(ID i);
}
package com.rns.testes.java.service.impl;

import org.springframework.stereotype.Service;

import com.rns.testes.java.dao.IFilialDAO;
import com.rns.testes.java.model.Filial;
import com.rns.testes.java.service.AbstractGenericServicePersistence;
import com.rns.testes.java.service.IFilialService;

@Service
public class FilialService extends AbstractGenericServicePersistence<IFilialDAO, Filial, Long> implements IFilialService{}
package com.rns.testes.java.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import com.rns.testes.java.dto.ProdutoDTO;
import com.rns.testes.java.exception.ResourceNotFoundException;
import com.rns.testes.java.exception.ResponseMessage;
import com.rns.testes.java.mapper.ProdutoMapper;
import com.rns.testes.java.model.Produto;

@CrossOrigin
@RestController
@RequestMapping("/produtos")
public class ProdutoController extends AbstractController<Produto, ProdutoDTO, String>{    
    @Autowired
	private ResponseMessage responseMessage;
    
    public ProdutoController() {
    	super(new ProdutoMapper());
    }
    
    @ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<Object> handleResourceNotFound(ResourceNotFoundException ex, WebRequest request){
		return responseMessage.error(HttpStatus.NOT_FOUND, "produto-not-found");
	}
    
    @ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<Object> handleDataIntegrityViolation(DataIntegrityViolationException ex, WebRequest request){
		return responseMessage.error(HttpStatus.INTERNAL_SERVER_ERROR, "produto-not-allowed-delete");
	}
}
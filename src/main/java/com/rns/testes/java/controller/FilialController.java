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

import com.rns.testes.java.dto.FilialDTO;
import com.rns.testes.java.exception.ResourceNotFoundException;
import com.rns.testes.java.exception.ResponseMessage;
import com.rns.testes.java.mapper.FilialMapper;
import com.rns.testes.java.model.Filial;

@CrossOrigin
@RestController
@RequestMapping("/filiais")
public class FilialController extends AbstractController<Filial, FilialDTO, Long>{
    @Autowired
	private ResponseMessage responseMessage;
    
    public FilialController() {
    	super(new FilialMapper());
    }
    
    @ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<Object> handleResourceNotFound(ResourceNotFoundException ex, WebRequest request){
		return responseMessage.error(HttpStatus.NOT_FOUND, "filial-not-found");
	}
    
    @ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<Object> handleDataIntegrityViolation(DataIntegrityViolationException ex, WebRequest request){
		return responseMessage.error(HttpStatus.INTERNAL_SERVER_ERROR, "filial-not-allowed-delete");
	}
}
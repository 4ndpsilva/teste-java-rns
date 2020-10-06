package com.rns.testes.java.exception;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.rns.testes.java.dto.ErrorDTO;

@RestControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler{
	@Autowired
	private ResponseMessage responseMessage;
	
	@Autowired
	private MessageSource messageSource;
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		return handleExceptionInternal(ex, getErrors(ex.getBindingResult()), headers, HttpStatus.BAD_REQUEST, request);
	}
	
	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		return responseMessage.error(HttpStatus.BAD_REQUEST, "invalid-param");
	} 
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<Object> handleResourceNotFound(ResourceNotFoundException ex, WebRequest request){
		return responseMessage.error(HttpStatus.NOT_FOUND, "resource-not-found");
	}
	
	@ExceptionHandler(NumberFormatException.class)
	public ResponseEntity<Object> handleNumerFormat(NumberFormatException ex, WebRequest request){
		return responseMessage.error(HttpStatus.BAD_REQUEST, "invalid-number");
	}
	
	@ExceptionHandler(UnsupportedOperationException.class)
	public ResponseEntity<Object> handleUnsupportedOperation(UnsupportedOperationException ex, WebRequest request){
		return responseMessage.error(HttpStatus.BAD_REQUEST, "id-not-exist");
	}
	
	@ExceptionHandler(ConflictIdException.class)
	public ResponseEntity<Object> handleConflictId(ConflictIdException ex, WebRequest request){
		return responseMessage.error(HttpStatus.CONFLICT, "conflict-id");
	}
	
	private List<ErrorDTO> getErrors(BindingResult bindingResult){
		List<ErrorDTO> errors = new ArrayList<>();
		
		for(FieldError fieldError : bindingResult.getFieldErrors()) {
			String msgError = messageSource.getMessage(fieldError, LocaleContextHolder.getLocale());
			ErrorDTO error = new ErrorDTO(msgError, fieldError.getField());
			error.setOccurredAt(LocalDateTime.now());
			error.setStatusCode(HttpStatus.BAD_REQUEST.value());
			errors.add(error);
		}
			
		return errors;
	} 
}
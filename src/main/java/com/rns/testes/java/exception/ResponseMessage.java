package com.rns.testes.java.exception;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.rns.testes.java.dto.ErrorDTO;

@Component
public class ResponseMessage {
	@Autowired
	private MessageSource messageSource;
	
	public ResponseEntity<Object> error(HttpStatus httpStatus, String msg){
		ErrorDTO error = new ErrorDTO(httpStatus.value(), getMessage(msg), LocalDateTime.now());
		List<ErrorDTO> errors = Arrays.asList(error);
		return ResponseEntity.status(httpStatus).body(errors);
	}
	
	private String getMessage(String message) {
		return messageSource.getMessage(message, null, LocaleContextHolder.getLocale());
	}
}
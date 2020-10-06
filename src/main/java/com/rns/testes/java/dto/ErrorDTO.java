package com.rns.testes.java.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ErrorDTO {
	private int statusCode;
	private String message;
	private String field;
	private LocalDateTime occurredAt;
	
	public ErrorDTO(String message) {
		this.message = message;
	}
	
	public ErrorDTO(String message, String field) {
		this.message = message;
		this.field = field;
	}
	
	public ErrorDTO(int statusCode, String message, LocalDateTime occurredAt) {
		this.statusCode = statusCode;
		this.message = message;
		this.occurredAt = occurredAt;
	}
}
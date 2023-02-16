package com.mx.jh.escuela.util.exception;

import com.mx.jh.escuela.util.message.AplicationMessage;

import lombok.Getter;

@Getter
public class CalificacionException extends RuntimeException {
    
    private final AplicationMessage aplicationMessage;
	
	public CalificacionException(AplicationMessage aplicationMessage) {
		super(aplicationMessage.getMessage());
		this.aplicationMessage = aplicationMessage;
	}
}

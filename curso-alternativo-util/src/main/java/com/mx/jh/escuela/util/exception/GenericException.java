package com.mx.jh.escuela.util.exception;

import com.mx.jh.escuela.util.message.AplicationMessage;

import lombok.Getter;

@Getter
public class GenericException extends RuntimeException{
    private final AplicationMessage aplicationMessage;
	
	public GenericException(AplicationMessage aplicationMessage) {
		super(aplicationMessage.getMessage());
		this.aplicationMessage = aplicationMessage;
	}
}

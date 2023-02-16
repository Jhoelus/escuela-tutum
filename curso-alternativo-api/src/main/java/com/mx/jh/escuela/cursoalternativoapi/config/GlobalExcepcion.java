package com.mx.jh.escuela.cursoalternativoapi.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.mx.jh.escuela.cursoalternativomodel.dtos.response.BaseResponse;
import com.mx.jh.escuela.util.exception.CalificacionException;
import com.mx.jh.escuela.util.exception.GenericException;
import com.mx.jh.escuela.util.message.AplicationMessage;

@ControllerAdvice
public class GlobalExcepcion extends ResponseEntityExceptionHandler {

	@ExceptionHandler(CalificacionException.class)
	@ResponseBody
	public ResponseEntity<Object> constraintViolationException(CalificacionException ex) {

		BaseResponse<String> response = new BaseResponse<>();
		response.setCode(ex.getAplicationMessage().getCode());
		response.setMessage(ex.getAplicationMessage().getMessage());
		response.getErrors().add(ex.getAplicationMessage().getMessage());
		response.setSuccess("FALSE");

		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(GenericException.class)
	@ResponseBody
	public ResponseEntity<Object> constraintViolationException2(GenericException ex) {

		BaseResponse<String> response = new BaseResponse<>();
		response.setCode(ex.getAplicationMessage().getCode());
		response.setMessage(ex.getAplicationMessage().getMessage());
		response.getErrors().add(ex.getAplicationMessage().getMessage());
		response.setSuccess("FALSE");

		return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@Override
	@ResponseBody
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		BaseResponse<String> response = new BaseResponse<>();
		response.setCode(AplicationMessage.MESSAGE_ERROR_DEFAULT.getCode());
		response.setMessage(AplicationMessage.MESSAGE_ERROR_DEFAULT.getMessage());

		List<String> errores = new ArrayList<String>();

		for (FieldError fieldError : ex.getBindingResult().getFieldErrors()) {
			errores.add(fieldError.getField() + " : " + fieldError.getDefaultMessage());
		}
		
		response.setSuccess("FALSE");
		response.setErrors(errores);

		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}
}

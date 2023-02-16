package com.mx.jh.escuela.util.message;

import lombok.Getter;

@Getter
public enum AplicationMessage {

	MESSAGE_EXITO_DEFAULT(0, "Operación realizada correctamente."),
	MESSAGE_ERROR_DEFAULT(1, "Operación incorrecta"),
	MESSAGE_ERROR_INESPERADO(2, "Error inesperado, consulte administrador"),

	MESSAGE_ALUMNO_ERROR_1(20, "Alumno no valido"),
	MESSAGE_MATERIA_ERROR_1(30, "Materia no valida"),
	MESSAGE_CALIFICACION_ERROR_1(40, "Esta Calificación ya fue asignada previamente."),
	MESSAGE_CALIFICACION_ERROR_2(41, "Esta Calificación aún no es asignada."),
	MESSAGE_CALIFICACION_ERROR_3(42, "Id de calificación no valida.");



	Integer code;
	String message;

	AplicationMessage(Integer code, String message) {
		this.code = code;
		this.message = message;
	}

}

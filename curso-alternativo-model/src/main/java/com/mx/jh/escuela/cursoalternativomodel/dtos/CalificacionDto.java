package com.mx.jh.escuela.cursoalternativomodel.dtos;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class CalificacionDto implements Serializable {
    
    private Long id;
    private AlumnoDto alumno;
    private MateriaDto materia;
    double calificacion;

	@JsonFormat(pattern = "dd/MM/yyyy", timezone = "America/Mexico_City")
    private Date fechaRegistro;
    
}

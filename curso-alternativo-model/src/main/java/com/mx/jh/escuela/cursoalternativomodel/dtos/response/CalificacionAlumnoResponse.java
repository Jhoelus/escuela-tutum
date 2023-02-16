package com.mx.jh.escuela.cursoalternativomodel.dtos.response;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mx.jh.escuela.cursoalternativomodel.dtos.CalificacionDto;

import lombok.Data;

@Data
public class CalificacionAlumnoResponse implements Serializable {
    
    @JsonProperty("calificaciones")
    private List<CalificacionDto> calificacionDto;
    
    private double promedio;
}

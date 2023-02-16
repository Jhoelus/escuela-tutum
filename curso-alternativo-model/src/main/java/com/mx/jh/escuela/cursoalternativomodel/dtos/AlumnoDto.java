package com.mx.jh.escuela.cursoalternativomodel.dtos;

import java.io.Serializable;

import lombok.Data;

@Data
public class AlumnoDto implements Serializable {
    
    private Long id;
    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private int activo;
}

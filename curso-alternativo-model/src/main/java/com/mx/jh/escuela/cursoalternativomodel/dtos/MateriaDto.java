package com.mx.jh.escuela.cursoalternativomodel.dtos;

import java.io.Serializable;

import lombok.Data;

@Data
public class MateriaDto implements Serializable {
    
    private Long id;
    private String nombre;
    private int activo;
}

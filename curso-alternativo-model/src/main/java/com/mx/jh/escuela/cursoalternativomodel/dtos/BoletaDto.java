package com.mx.jh.escuela.cursoalternativomodel.dtos;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class BoletaDto implements Serializable {
    
    private Long id;
    private String materia;
    private double calificacion;

}

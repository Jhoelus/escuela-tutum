package com.mx.jh.escuela.cursoalternativomodel.dtos.request;

import java.io.Serializable;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import lombok.Data;

@Data
public class CalificacionRequest implements Serializable {
    
    public interface CalificacionCreate{}
    public interface CalificacionUpdate{}

    @NotNull(groups = {CalificacionCreate.class,CalificacionUpdate.class}, message = "campo idAlumno invalido")
    @Positive(groups = {CalificacionCreate.class,CalificacionUpdate.class}, message = "campo idAlumno invalido")
    private Long idAlumno;

    @NotNull(groups = {CalificacionCreate.class,CalificacionUpdate.class}, message = "campo idMateria invalido")
    @Positive(groups = {CalificacionCreate.class,CalificacionUpdate.class}, message = "campo idMateria invalido")
    private Long idMateria;

    @NotNull(groups = {CalificacionCreate.class,CalificacionUpdate.class}, message = "campo calificacion invalido")
    @Positive(groups = {CalificacionCreate.class,CalificacionUpdate.class}, message = "campo calificacion invalido")
    @Digits(groups = {CalificacionCreate.class,CalificacionUpdate.class}, integer=2, fraction=2, message = "campo calificacion invalido")
    @DecimalMax(groups = {CalificacionCreate.class,CalificacionUpdate.class}, value = "10.0", message = "campo calificacion tiene que ser maximo 10")
    @DecimalMin(groups = {CalificacionCreate.class,CalificacionUpdate.class}, value = "1.0", message = "campo calificacion invalido")
    double calificacion;
}

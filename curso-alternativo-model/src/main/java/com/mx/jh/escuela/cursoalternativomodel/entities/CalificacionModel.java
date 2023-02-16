package com.mx.jh.escuela.cursoalternativomodel.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Entity
@Table(name = "t_calificaciones")
@Data
public class CalificacionModel implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_t_calificaciones", nullable = false, updatable=false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_t_usuarios", nullable = false, updatable = false)
    private AlumnoModel alumno;
    
    @ManyToOne(optional = false)
    @JoinColumn(name = "id_t_materias", nullable = false, updatable = false)
    private MateriaModel materia;

    // precision=10, scale=2,  
    @Column(nullable = false, columnDefinition="Decimal(10,2)")
    double calificacion;

    @Column(name = "fecha_registro", nullable = false, updatable=false)
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date fechaRegistro;

    @PrePersist
    private void setCreationDate() {
        this.fechaRegistro = new Date();
    }
}

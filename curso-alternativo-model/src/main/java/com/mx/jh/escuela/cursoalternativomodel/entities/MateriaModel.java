package com.mx.jh.escuela.cursoalternativomodel.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "t_materias")
@Data
public class MateriaModel  implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_t_materias", nullable = false, updatable=false)
    private Long id;

    @Column(nullable = false, length = 80, columnDefinition="varchar(80)")
    private String nombre;

    @Column(nullable = false, length = 1)
    private int activo;

}

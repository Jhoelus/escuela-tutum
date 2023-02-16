package com.mx.jh.escuela.repository.alumno;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mx.jh.escuela.cursoalternativomodel.entities.AlumnoModel;

public interface AlumnoRepository extends JpaRepository<AlumnoModel, Long> {
    
}

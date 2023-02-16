package com.mx.jh.escuela.repository.calificacion;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mx.jh.escuela.cursoalternativomodel.entities.AlumnoModel;
import com.mx.jh.escuela.cursoalternativomodel.entities.CalificacionModel;
import com.mx.jh.escuela.cursoalternativomodel.entities.MateriaModel;

public interface CalificacionRepository extends JpaRepository<CalificacionModel, Long> {
    
    public List<CalificacionModel> findByAlumno(AlumnoModel alumno);
    public CalificacionModel findByAlumnoAndMateria(AlumnoModel alumno, MateriaModel materia);
}

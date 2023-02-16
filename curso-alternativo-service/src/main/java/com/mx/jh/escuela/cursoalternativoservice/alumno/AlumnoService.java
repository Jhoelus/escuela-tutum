package com.mx.jh.escuela.cursoalternativoservice.alumno;

import java.util.List;
import java.util.Optional;

import com.mx.jh.escuela.cursoalternativomodel.entities.AlumnoModel;

public interface AlumnoService {

    List<AlumnoModel> findAll();
    Optional<AlumnoModel> findById(Long id);
    AlumnoModel save(AlumnoModel user);
    AlumnoModel update(AlumnoModel user);
    void delete(Long id);
    
}

package com.mx.jh.escuela.repository.materia;

import com.mx.jh.escuela.cursoalternativomodel.entities.MateriaModel;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MateriaRepository extends JpaRepository<MateriaModel, Long> {
    
}

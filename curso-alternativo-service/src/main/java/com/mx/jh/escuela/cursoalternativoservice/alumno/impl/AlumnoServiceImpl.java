package com.mx.jh.escuela.cursoalternativoservice.alumno.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mx.jh.escuela.cursoalternativomodel.entities.AlumnoModel;
import com.mx.jh.escuela.cursoalternativoservice.alumno.AlumnoService;
import com.mx.jh.escuela.repository.alumno.AlumnoRepository;

@Service
public class AlumnoServiceImpl implements AlumnoService {

    @Autowired
    private AlumnoRepository alumnoRepository;

    @Override
    public void delete(Long id) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public List<AlumnoModel> findAll() {
        return alumnoRepository.findAll();
    }

    @Override
    public Optional<AlumnoModel> findById(Long id) {
        // TODO Auto-generated method stub
        return Optional.empty();
    }

    @Override
    public AlumnoModel save(AlumnoModel user) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public AlumnoModel update(AlumnoModel user) {
        // TODO Auto-generated method stub
        return null;
    }
    
}

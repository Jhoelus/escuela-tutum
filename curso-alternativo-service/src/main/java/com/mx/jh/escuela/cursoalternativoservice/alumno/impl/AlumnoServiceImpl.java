package com.mx.jh.escuela.cursoalternativoservice.alumno.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mx.jh.escuela.cursoalternativomodel.entities.AlumnoModel;
import com.mx.jh.escuela.cursoalternativoservice.alumno.AlumnoService;
import com.mx.jh.escuela.repository.alumno.AlumnoRepository;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class AlumnoServiceImpl implements AlumnoService {

    @Autowired
    private AlumnoRepository alumnoRepository;

    @Override
    public void delete(Long id) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public List<AlumnoModel> findAll() {
        List<AlumnoModel> alumnos = alumnoRepository.findAll();
        log.info("Se encontrarion: " + alumnos.size());
        return alumnos;
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

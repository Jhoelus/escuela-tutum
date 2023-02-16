package com.mx.jh.escuela.cursoalternativoapi.api.alumno;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mx.jh.escuela.cursoalternativomodel.entities.AlumnoModel;
import com.mx.jh.escuela.cursoalternativoservice.alumno.AlumnoService;

@RestController
@RequestMapping("/alumnos")
public class AlumnoController {

    @Autowired
    private AlumnoService alumnoService;

    @GetMapping({ "/", "" })
    public ResponseEntity<List<AlumnoModel>> getAlumnos() {
        return ResponseEntity.ok(alumnoService.findAll());
    }
}

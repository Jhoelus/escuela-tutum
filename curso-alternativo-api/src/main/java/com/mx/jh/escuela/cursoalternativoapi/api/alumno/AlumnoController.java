package com.mx.jh.escuela.cursoalternativoapi.api.alumno;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mx.jh.escuela.cursoalternativomodel.dtos.AlumnoDto;
import com.mx.jh.escuela.cursoalternativomodel.dtos.response.BaseResponse;
import com.mx.jh.escuela.cursoalternativoservice.alumno.AlumnoService;

@RestController
@RequestMapping("/alumnos")
@CrossOrigin(origins = "http://localhost:4200/", maxAge = 3600)
public class AlumnoController {

    @Autowired
    private AlumnoService alumnoService;

    @GetMapping({ "/", "" })
    public ResponseEntity<BaseResponse<List<AlumnoDto>>> getAlumnos() {
        return ResponseEntity.ok(alumnoService.findAll());
    }

    @GetMapping("{idAlumno}/boleta")
    public ResponseEntity<BaseResponse<String>> getBoleta(@PathVariable("idAlumno") Long idAlumno) {
        return ResponseEntity.ok(alumnoService.findBoleta(idAlumno));
    }
}

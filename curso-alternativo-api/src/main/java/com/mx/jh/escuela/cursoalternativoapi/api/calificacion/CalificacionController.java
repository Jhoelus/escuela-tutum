package com.mx.jh.escuela.cursoalternativoapi.api.calificacion;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mx.jh.escuela.cursoalternativomodel.dtos.request.CalificacionRequest;
import com.mx.jh.escuela.cursoalternativomodel.dtos.request.CalificacionRequest.CalificacionAlta;
import com.mx.jh.escuela.cursoalternativomodel.dtos.response.BaseResponse;
import com.mx.jh.escuela.cursoalternativomodel.dtos.response.CalificacionAlumnoResponse;
import com.mx.jh.escuela.cursoalternativoservice.calificacion.CalificacionService;

@RestController
@RequestMapping("/calificaciones")
public class CalificacionController {
    
    @Autowired
    private CalificacionService calificacionService;

    @PostMapping
    public ResponseEntity<BaseResponse<Void>> save(@Validated (value = {CalificacionAlta.class }) @RequestBody CalificacionRequest calificacionRequest){
        return ResponseEntity.status(HttpStatus.CREATED).body(calificacionService.save(calificacionRequest));
    }

    @GetMapping
    public ResponseEntity<BaseResponse<CalificacionAlumnoResponse>> getCalificacionByIdAlumno(@RequestParam("idAlumno") Long idAlumno){
        return ResponseEntity.ok(calificacionService.findByIdAlumno(idAlumno));
    }
}

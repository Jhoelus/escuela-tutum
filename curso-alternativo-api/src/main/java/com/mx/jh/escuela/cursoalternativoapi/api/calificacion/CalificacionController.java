package com.mx.jh.escuela.cursoalternativoapi.api.calificacion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mx.jh.escuela.cursoalternativomodel.dtos.request.CalificacionRequest;
import com.mx.jh.escuela.cursoalternativomodel.dtos.request.CalificacionRequest.CalificacionCreate;
import com.mx.jh.escuela.cursoalternativomodel.dtos.request.CalificacionRequest.CalificacionUpdate;
import com.mx.jh.escuela.cursoalternativomodel.dtos.response.BaseResponse;
import com.mx.jh.escuela.cursoalternativomodel.dtos.response.CalificacionAlumnoResponse;
import com.mx.jh.escuela.cursoalternativoservice.calificacion.CalificacionService;

@RestController
@RequestMapping("/calificaciones")
@CrossOrigin(origins = "http://localhost:4200/", maxAge = 3600)
public class CalificacionController {
    
    @Autowired
    private CalificacionService calificacionService;

    @PostMapping
    public ResponseEntity<BaseResponse<Void>> save(@Validated (value = {CalificacionCreate.class }) @RequestBody CalificacionRequest calificacionRequest){
        return ResponseEntity.status(HttpStatus.CREATED).body(calificacionService.save(calificacionRequest));
    }

    @GetMapping
    public ResponseEntity<BaseResponse<CalificacionAlumnoResponse>> getCalificacionByIdAlumno(@RequestParam("idAlumno") Long idAlumno){
        return ResponseEntity.ok(calificacionService.findByIdAlumno(idAlumno));
    }

    @PutMapping
    public ResponseEntity<BaseResponse<Void>> update(@Validated (value = {CalificacionUpdate.class }) @RequestBody CalificacionRequest calificacionRequest){
        return ResponseEntity.ok(calificacionService.update(calificacionRequest));
    }

    @DeleteMapping("/{idCalificacion}")
    public ResponseEntity<BaseResponse<Void>> delete(@PathVariable("idCalificacion") Long idCalificacion) {
        return ResponseEntity.ok(calificacionService.delete(idCalificacion));
    }
}

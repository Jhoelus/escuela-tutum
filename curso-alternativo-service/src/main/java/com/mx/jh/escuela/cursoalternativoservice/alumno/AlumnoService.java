package com.mx.jh.escuela.cursoalternativoservice.alumno;

import java.util.List;

import com.mx.jh.escuela.cursoalternativomodel.dtos.AlumnoDto;
import com.mx.jh.escuela.cursoalternativomodel.dtos.response.BaseResponse;

public interface AlumnoService {

    BaseResponse<List<AlumnoDto>> findAll();
    BaseResponse<String> findBoleta(Long id);
}

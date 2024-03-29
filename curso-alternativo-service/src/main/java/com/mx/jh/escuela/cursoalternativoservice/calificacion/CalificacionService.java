package com.mx.jh.escuela.cursoalternativoservice.calificacion;

import com.mx.jh.escuela.cursoalternativomodel.dtos.request.CalificacionRequest;
import com.mx.jh.escuela.cursoalternativomodel.dtos.response.BaseResponse;
import com.mx.jh.escuela.cursoalternativomodel.dtos.response.CalificacionAlumnoResponse;

public interface CalificacionService {
    BaseResponse<CalificacionAlumnoResponse> findByIdAlumno(Long id);
    BaseResponse<Void> save(CalificacionRequest user);
    BaseResponse<Void> update(CalificacionRequest user);
    BaseResponse<Void> delete(Long id);
}

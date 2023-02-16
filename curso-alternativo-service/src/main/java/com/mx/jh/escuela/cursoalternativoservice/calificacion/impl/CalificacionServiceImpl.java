package com.mx.jh.escuela.cursoalternativoservice.calificacion.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mx.jh.escuela.cursoalternativomodel.dtos.CalificacionDto;
import com.mx.jh.escuela.cursoalternativomodel.dtos.request.CalificacionRequest;
import com.mx.jh.escuela.cursoalternativomodel.dtos.response.BaseResponse;
import com.mx.jh.escuela.cursoalternativomodel.dtos.response.CalificacionAlumnoResponse;
import com.mx.jh.escuela.cursoalternativomodel.entities.AlumnoModel;
import com.mx.jh.escuela.cursoalternativomodel.entities.CalificacionModel;
import com.mx.jh.escuela.cursoalternativomodel.entities.MateriaModel;
import com.mx.jh.escuela.cursoalternativoservice.calificacion.CalificacionService;
import com.mx.jh.escuela.repository.alumno.AlumnoRepository;
import com.mx.jh.escuela.repository.calificacion.CalificacionRepository;
import com.mx.jh.escuela.repository.materia.MateriaRepository;
import com.mx.jh.escuela.util.exception.CalificacionException;
import com.mx.jh.escuela.util.message.AplicationMessage;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class CalificacionServiceImpl implements CalificacionService {

    @Autowired
    CalificacionRepository calificacionRepository;

    @Autowired
    AlumnoRepository alumnoRepository;

    @Autowired
    MateriaRepository materiaRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    @Transactional
    public BaseResponse<Void> save(CalificacionRequest calificacionRequest) {

        BaseResponse<Void> response = null;

        Optional<AlumnoModel> alumnoOptional = alumnoRepository.findById(calificacionRequest.getIdAlumno());

        if (alumnoOptional.isPresent()) {
            Optional<MateriaModel> materiaOptional = materiaRepository.findById(calificacionRequest.getIdMateria());
            if (materiaOptional.isPresent()) {
                CalificacionModel newCalificacionRequest = new CalificacionModel();
                newCalificacionRequest.setAlumno(alumnoOptional.get());
                newCalificacionRequest.setMateria(materiaOptional.get());
                newCalificacionRequest.setCalificacion(calificacionRequest.getCalificacion());

                calificacionRepository.save(newCalificacionRequest);

                response = new BaseResponse<>();
                response.setCode(AplicationMessage.MESSAGE_EXITO_DEFAULT.getCode());
                response.setMessage(AplicationMessage.MESSAGE_EXITO_DEFAULT.getMessage());
                response.setSuccess("OK");
                return response;
            } else {
                throw new CalificacionException(AplicationMessage.MESSAGE_MATERIA_ERROR_1);
            }
        } else {
            throw new CalificacionException(AplicationMessage.MESSAGE_ALUMNO_ERROR_1);
        }
    }

    // @Override
    // public void delete(Long id) {
    //     // TODO Auto-generated method stub
        
    // }

    @Override
    public BaseResponse<CalificacionAlumnoResponse> findByIdAlumno(Long id) {
        
        log.info("Busqueda de calificacion del alumno con id: {}", id);

        BaseResponse<CalificacionAlumnoResponse> response = null;
        CalificacionAlumnoResponse calificacionAlumnoResponse = null;

        Optional<AlumnoModel> alumnoOptional = alumnoRepository.findById(id);
        
        if(alumnoOptional.isPresent()) {
            List<CalificacionModel> calificacionesBDD = calificacionRepository.findByAlumno(alumnoOptional.get());
            response = new BaseResponse<>();
            calificacionAlumnoResponse = new CalificacionAlumnoResponse();

            List<CalificacionDto> calificacionesDto = calificacionesBDD.stream()
                                    .map(calificacionBDD -> modelMapper.map(calificacionBDD, CalificacionDto.class))
                                    .collect(Collectors.toList());
            
            double promedio = calificacionesDto.stream()
                                .mapToDouble(CalificacionDto::getCalificacion)
                                .average().orElse(0);

            calificacionAlumnoResponse.setCalificacionDto(calificacionesDto);
            calificacionAlumnoResponse.setPromedio(promedio);


            response.setData(calificacionAlumnoResponse);
            response.setCode(AplicationMessage.MESSAGE_EXITO_DEFAULT.getCode());
            response.setMessage(AplicationMessage.MESSAGE_EXITO_DEFAULT.getMessage());
            response.setSuccess("OK");
            return response;

        } else {
            throw new CalificacionException(AplicationMessage.MESSAGE_ALUMNO_ERROR_1);
        }
    }

    // @Override
    // public CalificacionModel update(CalificacionModel user) {
    //     // TODO Auto-generated method stub
    //     return null;
    // }

    
}

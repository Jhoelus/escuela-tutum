package com.mx.jh.escuela.cursoalternativoservice.alumno.impl;

import java.io.ByteArrayOutputStream;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mx.jh.escuela.cursoalternativomodel.dtos.AlumnoDto;
import com.mx.jh.escuela.cursoalternativomodel.dtos.BoletaDto;
import com.mx.jh.escuela.cursoalternativomodel.dtos.response.BaseResponse;
import com.mx.jh.escuela.cursoalternativomodel.entities.AlumnoModel;
import com.mx.jh.escuela.cursoalternativomodel.entities.CalificacionModel;
import com.mx.jh.escuela.cursoalternativoservice.alumno.AlumnoService;
import com.mx.jh.escuela.repository.alumno.AlumnoRepository;
import com.mx.jh.escuela.repository.calificacion.CalificacionRepository;
import com.mx.jh.escuela.util.message.AplicationMessage;

import lombok.extern.log4j.Log4j2;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;

@Service
@Log4j2
public class AlumnoServiceImpl implements AlumnoService {

    @Autowired
    private AlumnoRepository alumnoRepository;

    @Autowired
    CalificacionRepository calificacionRepository;

    @Autowired
    private ModelMapper modelMapper;

    static final String URL_BOLETA_PDF = "reportes/boleta.jrxml";

    ClassLoader classLoader = getClass().getClassLoader();
    ByteArrayOutputStream output = new ByteArrayOutputStream();

    @Override
    @Transactional(readOnly = true)
    public BaseResponse<List<AlumnoDto>> findAll() {
        List<AlumnoModel> alumnosBDD = alumnoRepository.findAll();
        BaseResponse<List<AlumnoDto>> response = new BaseResponse<>();

        log.info("Se encontraron: " + alumnosBDD.size());

        List<AlumnoDto> alumnosDto = alumnosBDD.stream()
                .map(alumno -> modelMapper.map(alumno, AlumnoDto.class))
                .collect(Collectors.toList());
        
        response.setData(alumnosDto);
        response.setCode(AplicationMessage.MESSAGE_EXITO_DEFAULT.getCode());
        response.setMessage(AplicationMessage.MESSAGE_EXITO_DEFAULT.getMessage());
        response.setSuccess("OK");

        return response;
    }

    @Override
    public BaseResponse<String> findBoleta(Long id) {

        BaseResponse<String> response = new BaseResponse<>();
        List<BoletaDto> calificaciones = new ArrayList<>();
        Optional<AlumnoModel> alumnoOptional = alumnoRepository.findById(id);

        Map<String, Object> parametros = new HashMap<>();
        String boletaPdf = "";
        StringBuilder nombreAlumno = null;

        if (alumnoOptional.isPresent()) {
            AlumnoModel alumnoBDD = alumnoOptional.get();

            nombreAlumno = new StringBuilder();
            nombreAlumno.append(alumnoBDD.getNombre()).append(" ");
            nombreAlumno.append(alumnoBDD.getApellidoPaterno()).append(" ");
            nombreAlumno.append(alumnoBDD.getApellidoMaterno());

            List<CalificacionModel> calificacionesBDD = calificacionRepository.findByAlumno(alumnoOptional.get());

            calificacionesBDD.forEach(calificacion -> {
                calificaciones
                        .add(new BoletaDto(calificacion.getId(), calificacion.getMateria().getNombre().toUpperCase(),
                                calificacion.getCalificacion()));
            });

            Double promedio = calificacionesBDD.stream()
                    .mapToDouble(CalificacionModel::getCalificacion)
                    .average().orElse(0);

            BigDecimal promedioRedondeado = new BigDecimal(promedio).setScale(2, RoundingMode.HALF_UP);

            try {
                parametros.put("nombreAlumno", nombreAlumno.toString().toUpperCase());
                parametros.put("materias", calificaciones);
                parametros.put("rutaImg", "src/main/resources/reportes/waves.jpg");
                parametros.put("promedio", promedioRedondeado.doubleValue());

                log.info(parametros);

                JasperReport reporte = JasperCompileManager
                        .compileReport(classLoader.getResourceAsStream(URL_BOLETA_PDF));

                JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, parametros, new JREmptyDataSource());
                // new JRBeanCollectionDataSource(calificaciones) -- listas
                JasperExportManager.exportReportToPdfStream(jasperPrint, output);
                byte[] fileArray = output.toByteArray();

                output.reset();

                if (fileArray != null) {
                    boletaPdf = Base64.getEncoder().encodeToString(fileArray);
                }

                response.setData(boletaPdf);
                response.setCode(AplicationMessage.MESSAGE_EXITO_DEFAULT.getCode());
                response.setMessage(AplicationMessage.MESSAGE_EXITO_DEFAULT.getMessage());
                response.setSuccess("OK");

            } catch (Exception ex) {
                ex.printStackTrace();

                response.setData(null);
                response.setCode(AplicationMessage.MESSAGE_ERROR_INESPERADO.getCode());
                response.setMessage(AplicationMessage.MESSAGE_ERROR_INESPERADO.getMessage());
                response.setSuccess("BAD");
                response.getErrors().add("Error al construir Boleta");
            }
        }

        return response;
    }
}

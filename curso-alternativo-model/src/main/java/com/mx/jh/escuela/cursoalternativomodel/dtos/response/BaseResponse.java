package com.mx.jh.escuela.cursoalternativomodel.dtos.response;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class BaseResponse <T> {
    
    private Integer code;
    private String  message;
    private String  success;
    private T data;
    private List<String> errors = new ArrayList<>();

}

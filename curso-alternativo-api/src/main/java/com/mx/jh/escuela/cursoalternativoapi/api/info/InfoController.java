package com.mx.jh.escuela.cursoalternativoapi.api.info;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/info")
public class InfoController {

    @GetMapping({"/",""})
    public ResponseEntity<String> getStatusApi(){
        return ResponseEntity.ok("API UP....!!!!");
    }
}

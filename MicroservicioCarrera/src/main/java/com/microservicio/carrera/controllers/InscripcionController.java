package com.microservicio.carrera.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microservicio.carrera.dto.InscripcionDto;

import com.microservicio.carrera.services.InscripcionService;

@RestController
@RequestMapping("/inscripciones")
public class InscripcionController {

    @Autowired
    private InscripcionService inscripcionService;

    @GetMapping
    public List<InscripcionDto> getAllInscripciones(){
        return inscripcionService.getAllInscripciones();
    }

    @PostMapping
    public InscripcionDto createInscripcion(@RequestBody InscripcionDto inscripcion){
       
        return inscripcionService.createInscripcion(inscripcion);
    }

    
    

    
}

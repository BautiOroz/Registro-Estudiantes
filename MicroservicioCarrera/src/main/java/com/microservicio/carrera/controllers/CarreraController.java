package com.microservicio.carrera.controllers;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microservicio.carrera.dto.CarreraDto;
import com.microservicio.carrera.dto.EstudianteDto;
import com.microservicio.carrera.services.CarreraService;

@RestController
@RequestMapping("/carreras")
public class CarreraController {

    @Autowired
    private CarreraService carreraService;

    @GetMapping
    public List<CarreraDto> getAllCarreras(){
        return carreraService.getAllUsers();
    }

    @PostMapping
    public CarreraDto createCarrera(@RequestBody CarreraDto carrera){
        return carreraService.createCarrera(carrera);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CarreraDto> getCarreraById(@PathVariable Long id){
        return carreraService.findById(id).map(ResponseEntity::ok)
        .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/{ciudad}/{carreraId}")
    public List<EstudianteDto> estudiantesPorCarreraPorCiudad(@PathVariable String ciudad, @PathVariable Long carreraId){
        return carreraService.estudiantesPorCarreraCiudad(ciudad, carreraId);
    }

    @GetMapping("/generarReporte")
    public String prueba(){
        return carreraService.generarReporte();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCarrera(@PathVariable Long id){
        return carreraService.deleteCarrera(id)    
            ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
            : ResponseEntity.notFound().build();
    }

    
}

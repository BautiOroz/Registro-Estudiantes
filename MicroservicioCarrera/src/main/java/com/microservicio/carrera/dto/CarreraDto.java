package com.microservicio.carrera.dto;

import com.microservicio.carrera.models.CarreraModel;

public class CarreraDto {

    private Long id;

    private String nombre; 

    public CarreraDto(String nombre){
        this.nombre = nombre;
    }

    public CarreraDto(){
        
    }

    public CarreraDto(CarreraModel carrera){
        this.id = carrera.getId();
        this.nombre = carrera.getNombre();
    }

    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    
}

package com.microservicio.carrera.models;

import java.util.ArrayList;

import java.util.List;

import com.microservicio.carrera.dto.CarreraDto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "Carreras")
public class CarreraModel {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre; 

    @OneToMany(mappedBy = "carrera") 
    private List<InscripcionModel> inscriptos = new ArrayList<>();

    public CarreraModel() {

    }

    public CarreraModel(String nombre) {
        this.nombre = nombre;
    }

    public CarreraModel(CarreraDto carrera){
        this.nombre = carrera.getNombre();
    }

    @Override
    public String toString() {
        return "Carrera [id=" + id + ", nombre=" + nombre + "]";
    }
    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

}

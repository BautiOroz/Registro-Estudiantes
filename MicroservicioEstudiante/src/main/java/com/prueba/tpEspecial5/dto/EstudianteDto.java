package com.prueba.tpEspecial5.dto;

import com.prueba.tpEspecial5.models.EstudianteModel;

public class EstudianteDto {

    private Long nroLibreta;

    private String nombre; 

    private String apellido;

    private int dni;

    private int edad; 

    private String genero;

    private String ciudad;

    public EstudianteDto() {
    }

    public EstudianteDto(String nombre, String apellido, int dni,int edad, String genero, String ciudad) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.genero = genero;
        this.ciudad = ciudad;
        this.edad = edad;
    }

    public EstudianteDto(EstudianteModel estudiante){
        this.nroLibreta = estudiante.getNroLibreta();
        this.nombre = estudiante.getNombre();
        this.apellido = estudiante.getApellido();
        this.dni = estudiante.getDni();
        this.genero = estudiante.getGenero();
        this.ciudad = estudiante.getCiudad();
        this.edad = estudiante.getEdad();
    }

    public Long getNroLibreta() {
        return nroLibreta;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public int getDni() {
        return dni;
    }

    public int getEdad() {
        return edad;
    }

    public String getGenero() {
        return genero;
    }

    public String getCiudad() {
        return ciudad;
    }

    
}

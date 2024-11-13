package com.microservicio.carrera.dto;

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

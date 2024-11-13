package com.prueba.tpEspecial5.models;





import org.hibernate.annotations.Check;

import com.prueba.tpEspecial5.dto.EstudianteDto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;



@Entity
@Table(name = "Estudiantes")
@Check(constraints = "genero IN ('M', 'F')")
public class EstudianteModel {
   
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long nroLibreta;

    @Column(nullable = false)
    private String nombre; 

    @Column(nullable = false)
    private String apellido;

    @Column(name = "nroDocumento",unique = true) // un int no puede ser null, por defecto un 0
    private int dni;

    @Column(nullable = false)
    private int edad; 

    @Column(nullable = false)
    private String genero;

    @Column(nullable = false)
    private String ciudad;

    public EstudianteModel() {
    }

    public EstudianteModel(String nombre, String apellido, int dni,int edad, String genero, String ciudad) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.genero = genero;
        this.ciudad = ciudad;
        this.edad = edad;
    }

    public EstudianteModel(EstudianteDto estudiante){
        this.nombre = estudiante.getNombre();
        this.apellido = estudiante.getApellido();
        this.dni = estudiante.getDni();
        this.genero = estudiante.getGenero();
        this.ciudad = estudiante.getCiudad();
        this.edad = estudiante.getEdad();
    }


    @Override
    public String toString() {
        return "Estudiante [nroLibreta=" + nroLibreta + ", nombre=" + nombre + ", apellido=" + apellido + ", dni=" + dni
                + ", edad=" + edad + ", genero=" + genero + ", ciudad=" + ciudad + "]";
    }

    public Long getNroLibreta() {
        return this.nroLibreta;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public int getDni() {
        return dni;
    }

    public void setDni(int dni){
        this.dni = dni;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    
}

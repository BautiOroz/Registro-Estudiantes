package com.microservicio.carrera.dto;

import java.util.Date;

import com.microservicio.carrera.models.InscripcionModel;

public class InscripcionDto {

    private Date fecha_inscripcion;
    
    private boolean graduado;

    private Date fecha_graduacion;

    private Long estudianteId;

    private Long carreraId;

    public InscripcionDto(){

    }

    public InscripcionDto(InscripcionModel inscripcion){
        this.graduado = inscripcion.isGraduado();
        this.fecha_graduacion = inscripcion.getFechaGraduacion();
        this.fecha_inscripcion = inscripcion.getFechaInscripcion();
        this.carreraId = inscripcion.getCarreraId();
        this.estudianteId = inscripcion.getEstudianteId();
    }

    public Date getFecha_inscripcion() {
        return fecha_inscripcion;
    }

    public boolean isGraduado() {
        return graduado;
    }

    public Date getFecha_graduacion() {
        return fecha_graduacion;
    }

    public Long getEstudianteId() {
        return estudianteId;
    }

    public Long getCarreraId() {
        return carreraId;
    }

    public void setGraduado(boolean aux){
        this.graduado = aux;
    }
    
}

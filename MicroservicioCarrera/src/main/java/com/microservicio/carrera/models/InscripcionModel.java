package com.microservicio.carrera.models;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.microservicio.carrera.dto.InscripcionDto;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "Inscripciones")
public class InscripcionModel {

    @EmbeddedId
    private InscripcionIdModel id;

    
    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date fecha_inscripcion; 

    @Column(nullable = false)
    private boolean graduado;  

    @Column 
    @Temporal(TemporalType.DATE)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date fecha_graduacion;

    //@ManyToOne
    //@MapsId("estudianteId")  // Esto mapea el estudianteId en InscripcionId
    //@JoinColumn(name = "estudiante_id")
    //private Long estudiante;

    @ManyToOne
    @MapsId("carreraId")  // Esto mapea el carreraId en InscripcionId
    @JoinColumn(name = "carrera_id")
    private CarreraModel carrera;

    public InscripcionModel() {
    }

    public InscripcionModel(Long estudiante, CarreraModel carrera) {
        this.id = new InscripcionIdModel(estudiante, carrera.getId());
        
        this.carrera = carrera;
        this.fecha_inscripcion = new Date();
        this.graduado = false;
    }

    public InscripcionModel(InscripcionDto inscripcion, CarreraModel carrera){
        this.id = new InscripcionIdModel(inscripcion.getEstudianteId(), carrera.getId());
        this.carrera = carrera;
        this.fecha_inscripcion = inscripcion.getFecha_inscripcion();
        this.fecha_graduacion = inscripcion.getFecha_graduacion();
        this.graduado = inscripcion.isGraduado();
    }

    public void setEstudianteId(Long id){
        this.id.setEstudianteId(id);
    }

    public void setCarrera(CarreraModel carrera){
        this.carrera = carrera;
    }

    public Long getCarreraId(){
        return id.getCarreraId();
    }

    public Long getEstudianteId(){
        return id.getEstudianteId();
    }

    @Override
    public String toString() {
        return "Inscripcion [id=" + id + ", fecha_inscripcion=" + fecha_inscripcion + ", graduado=" + graduado
                + ", fecha_graduacion=" + fecha_graduacion + "]";
    }

    public InscripcionIdModel getId() {
        return id;
    }

    public Date getFechaInscripcion() {
        return fecha_inscripcion;
    }

    public void setFechaInscripcion(Date fecha_inscripcion) {
        this.fecha_inscripcion = fecha_inscripcion;
    }

    public boolean isGraduado() {
        return graduado;
    }
    
    public void setGraduado(boolean graduado) {
        this.graduado = graduado;
    }

    public Date getFechaGraduacion() {
        return fecha_graduacion;
    }

    public void setFechaGraduacion(Date fecha_graduacion) {
        this.fecha_graduacion = fecha_graduacion;
    }

    

    public CarreraModel getCarrera() {
        return carrera;
    }
}

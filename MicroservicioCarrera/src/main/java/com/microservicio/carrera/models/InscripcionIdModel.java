package com.microservicio.carrera.models;

import java.util.Objects;

import jakarta.persistence.Embeddable;

@Embeddable
public class InscripcionIdModel {
    private Long estudianteId;
    private Long carreraId;

    public InscripcionIdModel() {}
    public InscripcionIdModel(Long estudianteId, Long carreraId) {
        this.estudianteId = estudianteId;
        this.carreraId = carreraId;
    }

    public void setEstudianteId(Long id){
        this.estudianteId = id;
    }

    public Long getCarreraId(){
        return this.carreraId;
    }

    public Long getEstudianteId(){
        return this.estudianteId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InscripcionIdModel that = (InscripcionIdModel) o;
        return Objects.equals(estudianteId, that.carreraId) &&
               Objects.equals(carreraId, that.carreraId);
    }

    @Override
    public String toString() {
        return "InscripcionId [estudianteId=" + estudianteId + ", carreraId=" + carreraId + "]";
    }

    @Override
    public int hashCode() {
        return Objects.hash(carreraId, carreraId);
    }
}

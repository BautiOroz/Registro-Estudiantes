package com.microservicio.carrera.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.microservicio.carrera.models.InscripcionIdModel;
import com.microservicio.carrera.models.InscripcionModel;


@Repository
public interface IInscripcionRepository extends JpaRepository<InscripcionModel, InscripcionIdModel>{

    @Query("SELECT i FROM InscripcionModel i ORDER BY i.fecha_inscripcion ASC")
    List<InscripcionModel> findAllOrderByFechaInscripcion();

    @Query("SELECT i FROM InscripcionModel i ORDER BY i.fecha_graduacion ASC")
    List<InscripcionModel> findAllOrderByFechaGraduacion();
}

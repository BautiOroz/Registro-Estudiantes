package com.microservicio.carrera.repositories;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.microservicio.carrera.models.CarreraModel;



@Repository
public interface ICarreraRepository extends JpaRepository<CarreraModel, Long> {

    @Query("SELECT i.id.estudianteId FROM InscripcionModel i WHERE i.id.carreraId = ?1")
    List<Long> findEstudiantesByCarreraId(Long carreraId);
    
}

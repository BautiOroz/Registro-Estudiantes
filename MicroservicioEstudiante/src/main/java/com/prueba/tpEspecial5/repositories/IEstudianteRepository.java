package com.prueba.tpEspecial5.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Sort;

import com.prueba.tpEspecial5.models.EstudianteModel;

@Repository
public interface IEstudianteRepository extends JpaRepository<EstudianteModel, Long> {

    List<EstudianteModel> findByGenero(String genero);
    List<EstudianteModel> findByCiudad(String ciudad);
    List<EstudianteModel> findAll(Sort sort);
}

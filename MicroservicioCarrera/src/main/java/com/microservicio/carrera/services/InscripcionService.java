package com.microservicio.carrera.services;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.microservicio.carrera.dto.InscripcionDto;
import com.microservicio.carrera.models.CarreraModel;
import com.microservicio.carrera.models.InscripcionModel;
import com.microservicio.carrera.repositories.ICarreraRepository;
import com.microservicio.carrera.repositories.IInscripcionRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class InscripcionService {

    @Autowired
    private IInscripcionRepository inscripcionRepository;

    @Autowired
    private ICarreraRepository carreraRepository;

    @Autowired
    private RestTemplate restTemplate;

    public List<InscripcionDto> getAllInscripciones(){
        return inscripcionRepository.findAll().stream().map(InscripcionDto::new).toList();
    }

    public InscripcionDto createInscripcion(InscripcionDto inscripcion){
        String urlEstudiante = "http://localhost:4001/estudiantes/{id}";
        try {
            // Verificar si el estudiante existe
            restTemplate.getForObject(urlEstudiante, Void.class, inscripcion.getEstudianteId());
            CarreraModel carrera = carreraRepository.findById(inscripcion.getCarreraId()).orElseThrow(() -> new EntityNotFoundException("Carrera no encontrada"));
            if(inscripcion.getFecha_graduacion() != null){
                inscripcion.setGraduado(true);
            } else {inscripcion.setGraduado(false);}
            InscripcionModel inscrip = new InscripcionModel(inscripcion, carrera);
            inscrip.setCarrera(carrera);
            return new InscripcionDto(inscripcionRepository.save(inscrip));
        }catch (HttpClientErrorException.NotFound e) {
                throw new RuntimeException("Estudiante con ID " + inscripcion.getEstudianteId() + " no encontrado.");
        } catch (Exception e) {
            throw new RuntimeException("Error al crear la inscripción: " + e.getMessage());
        }

    }

    
}

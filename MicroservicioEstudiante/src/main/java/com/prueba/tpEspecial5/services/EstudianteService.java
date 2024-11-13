package com.prueba.tpEspecial5.services;

import com.prueba.tpEspecial5.dto.EstudianteDto;

import com.prueba.tpEspecial5.models.EstudianteModel;
import com.prueba.tpEspecial5.repositories.IEstudianteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class EstudianteService {

    @Autowired
    private IEstudianteRepository userRepository;

    // Obtener todos los usuarios
    public List<EstudianteDto> getAllUsers() {
        return userRepository.findAll().stream().map(EstudianteDto::new).toList();
    }

    public List<EstudianteDto> obtenerEstudiantesPorGenero(String genero) {
        return userRepository.findByGenero(genero).stream().map(EstudianteDto::new).toList();
    }

    public List<EstudianteDto> obtenerEstudiantesPorCiudad(String ciudad){
        return userRepository.findByCiudad(ciudad).stream().map(EstudianteDto::new).toList();
    }

    public List<EstudianteDto> obtenerEstudiantesOrdenados(String criterio) {
        Sort sort = Sort.by(criterio).ascending();
        return userRepository.findAll(sort).stream().map(EstudianteDto::new).toList();
    }

    // Crear un nuevo usuario
    public EstudianteDto createUser(EstudianteDto user) {
        EstudianteModel estudiante = new EstudianteModel(user);
        return new EstudianteDto(userRepository.save(estudiante));
    }

    // Obtener un usuario por ID
    public EstudianteDto getUserById(Long id) {
        return userRepository.findById(id).map(EstudianteDto::new).orElseThrow(
			() -> new IllegalArgumentException("ID de estudiante invalido: " + id));
    }

   

    // Eliminar un usuario por ID
    public boolean deleteUser(Long id) {
        return userRepository.findById(id).map(user -> {
            userRepository.delete(user);
            return true;
        }).orElse(false);
    }
}

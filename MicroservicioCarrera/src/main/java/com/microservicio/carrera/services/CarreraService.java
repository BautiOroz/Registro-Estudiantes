package com.microservicio.carrera.services;

import java.util.ArrayList;
import java.util.List;

import java.util.Optional;
import java.util.Date;
import java.util.Calendar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.data.domain.Sort;
import com.microservicio.carrera.dto.CarreraDto;
import com.microservicio.carrera.dto.EstudianteDto;

import com.microservicio.carrera.models.CarreraModel;
import com.microservicio.carrera.models.InscripcionModel;
import com.microservicio.carrera.repositories.ICarreraRepository;
import com.microservicio.carrera.repositories.IInscripcionRepository;

@Service
public class CarreraService {
    
    @Autowired
    private ICarreraRepository carreraRepository;

    @Autowired
    private IInscripcionRepository inscripcionRepository;

    @Autowired
    private RestTemplate restTemplate;

    public List<CarreraDto> getAllUsers() {
        return carreraRepository.findAll().stream().map(CarreraDto::new).toList();
    }

    public CarreraDto createCarrera(CarreraDto carrera){
        CarreraModel carrer = new CarreraModel(carrera);
        return new CarreraDto(carreraRepository.save(carrer));
    }

    public Optional<CarreraDto> findById(Long id){
        return carreraRepository.findById(id).map(CarreraDto::new);
    }

    public List<EstudianteDto> estudiantesPorCarreraCiudad(String ciudad, Long carreraId){
        String urlEstudiante = "http://localhost:4001/estudiantes/ciudad/{ciudad}";
        List<EstudianteDto> estudiantes = new ArrayList<>();
        List<Long> ids = carreraRepository.findEstudiantesByCarreraId(carreraId);
        ResponseEntity<List<EstudianteDto>> response = restTemplate.exchange(
        urlEstudiante, 
        HttpMethod.GET, 
        null, 
        new ParameterizedTypeReference<List<EstudianteDto>>() {}, 
        ciudad
        );
        List<EstudianteDto> estudiantesCiudad = response.getBody();
        for (Long long1 : ids) {
            for (EstudianteDto estudianteDto : estudiantesCiudad) {
                if(estudianteDto.getNroLibreta().equals(long1)){
                    estudiantes.add(estudianteDto);
                }
            }
            
        } 
            
        return estudiantes;
    }


    public String generarReporte(){
        StringBuilder reporte = new StringBuilder();
        String urlEstudiante = "http://localhost:4001/estudiantes/ordenados/{orden}";
        String orden = "nombre";
        ResponseEntity<List<EstudianteDto>> response = restTemplate.exchange(
        urlEstudiante, 
        HttpMethod.GET, 
        null, 
        new ParameterizedTypeReference<List<EstudianteDto>>() {}, 
        orden
        );
        Calendar calendar = Calendar.getInstance();
        List<InscripcionModel> inscripcionesInscripcion = inscripcionRepository.findAllOrderByFechaInscripcion();
        List<InscripcionModel> inscripcionesGraduacion = inscripcionRepository.findAllOrderByFechaGraduacion();
        List<EstudianteDto> estudiantes = response.getBody();
        List<InscripcionModel> inscriptos = new ArrayList();
        List<InscripcionModel> graduados = new ArrayList();
        for (InscripcionModel inscripcion : inscripcionesInscripcion) {
            if(!(inscripcion.isGraduado())){
                inscriptos.add(inscripcion);
            }
        }
        for (InscripcionModel inscripcion : inscripcionesGraduacion) {
            if(inscripcion.isGraduado()){
                graduados.add(inscripcion);
            }
        }
        Sort sort = Sort.by("nombre").ascending();
        List<CarreraModel> carreras = carreraRepository.findAll(sort);
        for (CarreraModel carreraModel : carreras) {
            reporte.append("Carrera: ").append(carreraModel.getNombre()).append("\n");
            reporte.append("  Inscriptos:\n");
            for (InscripcionModel inscrip : inscriptos) {
                if(carreraModel.getId().equals(inscrip.getCarreraId())){
                    for (EstudianteDto est : estudiantes) {
                        if(est.getNroLibreta().equals(inscrip.getEstudianteId())){
                            Date anio = inscrip.getFechaInscripcion();
                            calendar.setTime(anio);
                            int year = calendar.get(Calendar.YEAR);
                            reporte.append("      Año: ").append(year).append("\n");
                            agregarEstudiante(reporte, est);
                        }
                    }
                }
            }
            reporte.append("  Egresados:\n");
            for (InscripcionModel grad : graduados) {
                if(carreraModel.getId().equals(grad.getCarreraId())){
                    for (EstudianteDto est : estudiantes) {
                        if(est.getNroLibreta().equals(grad.getEstudianteId())){
                            Date anio = grad.getFechaGraduacion();
                            calendar.setTime(anio);
                            int year = calendar.get(Calendar.YEAR);
                            reporte.append("      Año: ").append(year).append("\n");
                            agregarEstudiante(reporte, est);
                        }
                    }
                }
            }
        }
        return reporte.toString();
    }

    public void agregarEstudiante(StringBuilder reporte, EstudianteDto estudiante){
        reporte.append("      Número de libreta: ").append(estudiante.getNroLibreta()).append("\n")
        .append("      Nombre: ").append(estudiante.getNombre()).append("\n")
        .append("      Apellido: ").append(estudiante.getApellido()).append("\n")
        .append("      Edad: ").append(estudiante.getEdad()).append("\n")
        .append("      Genero: ").append(estudiante.getGenero()).append("\n")
        .append("      Ciudad: ").append(estudiante.getCiudad()).append("\n")
        .append("      DNI: ").append(estudiante.getDni()).append("\n\n");
    }
    public boolean deleteCarrera(Long id){
        return carreraRepository.findById(id).map(carrera ->{
            carreraRepository.delete(carrera);
            return true;
        }).orElse(false);
    }
}

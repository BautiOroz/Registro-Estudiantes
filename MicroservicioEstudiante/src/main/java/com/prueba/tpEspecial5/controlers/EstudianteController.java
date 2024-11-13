package com.prueba.tpEspecial5.controlers;


import com.prueba.tpEspecial5.dto.EstudianteDto;
import com.prueba.tpEspecial5.services.EstudianteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/estudiantes")
public class EstudianteController {

    @Autowired
    private EstudianteService userService;

    
    @GetMapping
    public List<EstudianteDto> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/genero/{genero}")
    public ResponseEntity<List<EstudianteDto>> estudiantesPorGenero(@PathVariable String genero){
        List<EstudianteDto> estudiantes = userService.obtenerEstudiantesPorGenero(genero);
        return ResponseEntity.ok(estudiantes);
    }
    
    @GetMapping("/ordenados/{criterio}")
    public ResponseEntity<List<EstudianteDto>> obtenerEstudiantesOrdenados(@PathVariable String criterio) {
        List<EstudianteDto> estudiantes = userService.obtenerEstudiantesOrdenados(criterio);
        return ResponseEntity.ok(estudiantes);
    }

    @PostMapping
    public EstudianteDto createUser(@RequestBody EstudianteDto user) {
        return userService.createUser(user);
    }

    
    @GetMapping("/{id}")
    public ResponseEntity<EstudianteDto> getUserById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.getUserById(id));
    }

    @GetMapping("/ciudad/{ciudad}")
    public ResponseEntity<List<EstudianteDto>> estudiantePorCiudad(@PathVariable String ciudad){
        return ResponseEntity.status(HttpStatus.OK).body(userService.obtenerEstudiantesPorCiudad(ciudad));
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        return userService.deleteUser(id)
                ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
                : ResponseEntity.notFound().build();
    }
}

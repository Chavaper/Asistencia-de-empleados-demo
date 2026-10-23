package com.example.asistencias.Controller;

import com.example.asistencias.mapper.EmpleadoMapper;
import com.example.asistencias.modelo.Empleado;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/empleados")
public class EmpleadoController {

    private final EmpleadoMapper mapper;

    public EmpleadoController(EmpleadoMapper mapper) {
        this.mapper = mapper;
    }

    // GET: listar todos
    @GetMapping
    public List<Empleado> listar() {
        return mapper.listar();
    }

    // GET: buscar por ID
    @GetMapping("/{id}")
    public ResponseEntity<Empleado> buscarPorId(
            @PathVariable Integer id) {

        Empleado empleado = mapper.buscarPorId(id);

        if (empleado == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(empleado);
    }

    // POST: crear
    @PostMapping
    public ResponseEntity<Empleado> crear(
            @RequestBody Empleado empleado) {

        mapper.crear(empleado);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(empleado);
    }

    // PUT: actualizar
    @PutMapping("/{id}")
    public ResponseEntity<Void> actualizar(
            @PathVariable Integer id,
            @RequestBody Empleado empleado) {

        empleado.setId(id);

        int filas = mapper.actualizar(empleado);

        if (filas == 0) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.noContent().build();
    }

    // DELETE: eliminar
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(
            @PathVariable Integer id) {

        int filas = mapper.eliminar(id);

        if (filas == 0) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.noContent().build();
    }
}

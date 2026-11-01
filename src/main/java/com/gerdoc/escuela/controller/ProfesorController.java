package com.gerdoc.escuela.controller;

import com.gerdoc.escuela.model.Profesor;
import com.gerdoc.escuela.service.ProfesorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag( name = "Profesors", description = "Operaciones CRUD para la administración de Profesors")
@RestController
@RequestMapping("/api/Profesors")
@RequiredArgsConstructor
public class ProfesorController
{
    private final ProfesorService ProfesorService;

    @Operation(summary = "Consultar todos los Profesors", description = "Obtiene la lista completa de Profesors registrados")
    @GetMapping
    public ResponseEntity<List<Profesor>> findAll()
    {
        List<Profesor> Profesors = ProfesorService.findAll( );
        return ResponseEntity.ok(Profesors);
    }

    @Operation(summary = "Consultar un Profesor por ID", description = "Obtiene un Profesor por su ID")
    @GetMapping("/{id}")
    public ResponseEntity<Profesor> findById(@PathVariable Long id)
    {
        return ProfesorService.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Crear un nuevo Profesor", description = "Crea un nuevo Profesor en el sistema")
    @PostMapping
    public ResponseEntity<Profesor> create(@RequestBody Profesor Profesor)
    {
        Profesor.setId(null);
        Profesor nuevoProfesor = ProfesorService.save(Profesor);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(nuevoProfesor);
    }

    @Operation(summary = "Actualizar un Profesor existente", description = "Actualiza los datos de un Profesor existente en el sistema")
    @PutMapping("/{id}")
    public ResponseEntity<Profesor> update(@PathVariable Long id, @RequestBody Profesor Profesor)
    {
        return ProfesorService.findById(id)
                .map(ProfesorExistente -> {
                    ProfesorExistente.setNombre(Profesor.getNombre());
                    ProfesorExistente.setEspecialidad(Profesor.getEspecialidad());
                    Profesor actualizado = ProfesorService.save(ProfesorExistente);
                    return ResponseEntity.ok(actualizado);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Eliminar un Profesor por ID", description = "Elimina un Profesor del sistema por su ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id)
    {
        if (!ProfesorService.existsById(id))
        {
            return ResponseEntity.notFound().build();
        }
        ProfesorService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}

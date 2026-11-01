package com.gerdoc.escuela.controller;

import com.gerdoc.escuela.model.Materia;
import com.gerdoc.escuela.service.MateriaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag( name = "Materias", description = "Operaciones CRUD para la administración de Materias")
@RestController
@RequestMapping("/api/Materias")
@RequiredArgsConstructor
public class MateriaController
{
    private final MateriaService MateriaService;

    @Operation(summary = "Consultar todos los Materias", description = "Obtiene la lista completa de Materias registrados")
    @GetMapping
    public ResponseEntity<List<Materia>> findAll()
    {
        List<Materia> Materias = MateriaService.findAll( );
        return ResponseEntity.ok(Materias);
    }

    @Operation(summary = "Consultar un Materia por ID", description = "Obtiene un Materia por su ID")
    @GetMapping("/{id}")
    public ResponseEntity<Materia> findById(@PathVariable Long id)
    {
        return MateriaService.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Crear un nuevo Materia", description = "Crea un nuevo Materia en el sistema")
    @PostMapping
    public ResponseEntity<Materia> create(@RequestBody Materia Materia)
    {
        Materia.setId(null);
        Materia nuevoMateria = MateriaService.save(Materia);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(nuevoMateria);
    }

    @Operation(summary = "Actualizar un Materia existente", description = "Actualiza los datos de un Materia existente en el sistema")
    @PutMapping("/{id}")
    public ResponseEntity<Materia> update(@PathVariable Long id, @RequestBody Materia Materia)
    {
        return MateriaService.findById(id)
                .map(MateriaExistente -> {
                    MateriaExistente.setNombre(Materia.getNombre());
                    MateriaExistente.setCreditos(Materia.getCreditos());
                    Materia actualizado = MateriaService.save(MateriaExistente);
                    return ResponseEntity.ok(actualizado);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Eliminar un Materia por ID", description = "Elimina un Materia del sistema por su ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id)
    {
        if (!MateriaService.existsById(id))
        {
            return ResponseEntity.notFound().build();
        }
        MateriaService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}

package com.gerdoc.escuela.controller;

import com.gerdoc.escuela.model.ProfesorMateria;
import com.gerdoc.escuela.service.ProfesorMateriaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag( name = "ProfesorMaterias", description = "Operaciones CRUD para la administración de ProfesorMaterias")
@RestController
@RequestMapping("/api/ProfesorMaterias")
@RequiredArgsConstructor
public class ProfesorMateriaController
{
    private final ProfesorMateriaService ProfesorMateriaService;

    @Operation(summary = "Consultar todos los ProfesorMaterias", description = "Obtiene la lista completa de ProfesorMaterias registrados")
    @GetMapping
    public ResponseEntity<List<ProfesorMateria>> findAll()
    {
        List<ProfesorMateria> ProfesorMaterias = ProfesorMateriaService.findAll( );
        return ResponseEntity.ok(ProfesorMaterias);
    }

    @Operation(summary = "Consultar un ProfesorMateria por ID", description = "Obtiene un ProfesorMateria por su ID")
    @GetMapping("/{id}")
    public ResponseEntity<ProfesorMateria> findById(@PathVariable Long id)
    {
        return ProfesorMateriaService.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Crear un nuevo ProfesorMateria", description = "Crea un nuevo ProfesorMateria en el sistema")
    @PostMapping
    public ResponseEntity<ProfesorMateria> create(@RequestBody ProfesorMateria ProfesorMateria)
    {
        ProfesorMateria.setId(null);
        ProfesorMateria nuevoProfesorMateria = ProfesorMateriaService.save(ProfesorMateria);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(nuevoProfesorMateria);
    }

    @Operation(summary = "Actualizar un ProfesorMateria existente", description = "Actualiza los datos de un ProfesorMateria existente en el sistema")
    @PutMapping("/{id}")
    public ResponseEntity<ProfesorMateria> update(@PathVariable Long id, @RequestBody ProfesorMateria ProfesorMateria)
    {
        return ProfesorMateriaService.findById(id)
                .map(ProfesorMateriaExistente -> {
                    ProfesorMateriaExistente.setMateria(ProfesorMateria.getMateria());
                    ProfesorMateriaExistente.setProfesor(ProfesorMateria.getProfesor());
                    ProfesorMateria actualizado = ProfesorMateriaService.save(ProfesorMateriaExistente);
                    return ResponseEntity.ok(actualizado);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Eliminar un ProfesorMateria por ID", description = "Elimina un ProfesorMateria del sistema por su ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id)
    {
        if (!ProfesorMateriaService.existsById(id))
        {
            return ResponseEntity.notFound().build();
        }
        ProfesorMateriaService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}

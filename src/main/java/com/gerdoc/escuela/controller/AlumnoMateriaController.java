package com.gerdoc.escuela.controller;

import com.gerdoc.escuela.model.AlumnoMateria;
import com.gerdoc.escuela.service.AlumnoMateriaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag( name = "AlumnoMaterias", description = "Operaciones CRUD para la administración de AlumnoMaterias")
@RestController
@RequestMapping("/api/AlumnoMaterias")
@RequiredArgsConstructor
public class AlumnoMateriaController
{
    private final AlumnoMateriaService AlumnoMateriaService;

    @Operation(summary = "Consultar todos los AlumnoMaterias", description = "Obtiene la lista completa de AlumnoMaterias registrados")
    @GetMapping
    public ResponseEntity<List<AlumnoMateria>> findAll()
    {
        List<AlumnoMateria> AlumnoMaterias = AlumnoMateriaService.findAll( );
        return ResponseEntity.ok(AlumnoMaterias);
    }

    @Operation(summary = "Consultar un AlumnoMateria por ID", description = "Obtiene un AlumnoMateria por su ID")
    @GetMapping("/{id}")
    public ResponseEntity<AlumnoMateria> findById(@PathVariable Long id)
    {
        return AlumnoMateriaService.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Crear un nuevo AlumnoMateria", description = "Crea un nuevo AlumnoMateria en el sistema")
    @PostMapping
    public ResponseEntity<AlumnoMateria> create(@RequestBody AlumnoMateria AlumnoMateria)
    {
        AlumnoMateria.setId(null);
        AlumnoMateria nuevoAlumnoMateria = AlumnoMateriaService.save(AlumnoMateria);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(nuevoAlumnoMateria);
    }

    @Operation(summary = "Actualizar un AlumnoMateria existente", description = "Actualiza los datos de un AlumnoMateria existente en el sistema")
    @PutMapping("/{id}")
    public ResponseEntity<AlumnoMateria> update(@PathVariable Long id, @RequestBody AlumnoMateria AlumnoMateria)
    {
        return AlumnoMateriaService.findById(id)
                .map(AlumnoMateriaExistente -> {
                    AlumnoMateriaExistente.setMateria(AlumnoMateria.getMateria());
                    AlumnoMateriaExistente.setAlumno(AlumnoMateria.getAlumno());
                    AlumnoMateriaExistente.setCALIFICACION(AlumnoMateria.getCALIFICACION());
                    AlumnoMateria actualizado = AlumnoMateriaService.save(AlumnoMateriaExistente);
                    return ResponseEntity.ok(actualizado);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Eliminar un AlumnoMateria por ID", description = "Elimina un AlumnoMateria del sistema por su ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id)
    {
        if (!AlumnoMateriaService.existsById(id))
        {
            return ResponseEntity.notFound().build();
        }
        AlumnoMateriaService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}

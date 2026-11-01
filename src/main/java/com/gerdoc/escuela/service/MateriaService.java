package com.gerdoc.escuela.service;


import com.gerdoc.escuela.model.Materia;

import java.util.List;
import java.util.Optional;

public interface MateriaService
{
    // Consultar todos los Materias
    List<Materia> findAll();
    // Consultar un Materia por ID
    Optional<Materia> findById(Long id);
    // Guardar o actualizar un Materia
    Materia save(Materia Materia);
    // Eliminar un Materia por ID
    void deleteById(Long id);
    // Verificar si existe un Materia
    boolean existsById(Long id);
}

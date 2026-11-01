package com.gerdoc.escuela.service;


import com.gerdoc.escuela.model.Profesor;

import java.util.List;
import java.util.Optional;

public interface ProfesorService
{
    // Consultar todos los Profesors
    List<Profesor> findAll();
    // Consultar un Profesor por ID
    Optional<Profesor> findById(Long id);
    // Guardar o actualizar un Profesor
    Profesor save(Profesor Profesor);
    // Eliminar un Profesor por ID
    void deleteById(Long id);
    // Verificar si existe un Profesor
    boolean existsById(Long id);
}

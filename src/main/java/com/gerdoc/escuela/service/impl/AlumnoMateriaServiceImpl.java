package com.gerdoc.escuela.service.impl;

import com.gerdoc.escuela.model.AlumnoMateria;
import com.gerdoc.escuela.repository.AlumnoMateriaRepository;
import com.gerdoc.escuela.service.AlumnoMateriaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AlumnoMateriaServiceImpl implements AlumnoMateriaService
{
    private final AlumnoMateriaRepository AlumnoMateriaRepository;

    @Override
    @Transactional(readOnly = true)
    public List<AlumnoMateria> findAll()
    {
        return AlumnoMateriaRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<AlumnoMateria> findById(Long id)
    {
        return AlumnoMateriaRepository.findById(id);
    }

    @Override
    @Transactional
    public AlumnoMateria save(AlumnoMateria AlumnoMateria)
    {
        return AlumnoMateriaRepository.save(AlumnoMateria);
    }

    @Override
    @Transactional
    public void deleteById(Long id)
    {
        AlumnoMateriaRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existsById(Long id)
    {
        return AlumnoMateriaRepository.existsById(id);
    }
}

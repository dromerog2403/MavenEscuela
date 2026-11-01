package com.gerdoc.escuela.service.impl;

import com.gerdoc.escuela.model.ProfesorMateria;
import com.gerdoc.escuela.repository.ProfesorMateriaRepository;
import com.gerdoc.escuela.service.ProfesorMateriaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProfesorMateriaServiceImpl implements ProfesorMateriaService
{
    private final ProfesorMateriaRepository ProfesorMateriaRepository;

    @Override
    @Transactional(readOnly = true)
    public List<ProfesorMateria> findAll()
    {
        return ProfesorMateriaRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ProfesorMateria> findById(Long id)
    {
        return ProfesorMateriaRepository.findById(id);
    }

    @Override
    @Transactional
    public ProfesorMateria save(ProfesorMateria ProfesorMateria)
    {
        return ProfesorMateriaRepository.save(ProfesorMateria);
    }

    @Override
    @Transactional
    public void deleteById(Long id)
    {
        ProfesorMateriaRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existsById(Long id)
    {
        return ProfesorMateriaRepository.existsById(id);
    }
}

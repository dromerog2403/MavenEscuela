package com.gerdoc.escuela.service.impl;

import com.gerdoc.escuela.model.Profesor;
import com.gerdoc.escuela.repository.ProfesorRepository;
import com.gerdoc.escuela.service.ProfesorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProfesorServiceImpl implements ProfesorService
{
    private final ProfesorRepository ProfesorRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Profesor> findAll()
    {
        return ProfesorRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Profesor> findById(Long id)
    {
        return ProfesorRepository.findById(id);
    }

    @Override
    @Transactional
    public Profesor save(Profesor Profesor)
    {
        return ProfesorRepository.save(Profesor);
    }

    @Override
    @Transactional
    public void deleteById(Long id)
    {
        ProfesorRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existsById(Long id)
    {
        return ProfesorRepository.existsById(id);
    }
}

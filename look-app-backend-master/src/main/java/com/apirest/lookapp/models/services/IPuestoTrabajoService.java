package com.apirest.lookapp.models.services;

import com.apirest.lookapp.models.entity.PuestosTrabajo;
import java.util.List;

public interface IPuestoTrabajoService {

    public List<PuestosTrabajo> findAll();

    public PuestosTrabajo findById(Long id);

    public PuestosTrabajo save(PuestosTrabajo puestoTrabajo);

    public void deleteById(Long id);
}

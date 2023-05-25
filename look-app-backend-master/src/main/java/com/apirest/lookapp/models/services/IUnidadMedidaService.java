package com.apirest.lookapp.models.services;

import com.apirest.lookapp.models.entity.UnidadMedida;

import java.util.Collection;

public interface IUnidadMedidaService {
    public Collection<UnidadMedida> findAll();

    public UnidadMedida findById(Long id);

    public UnidadMedida save(UnidadMedida unidadMedida);

    public void deleteById(Long id);
}

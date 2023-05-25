package com.apirest.lookapp.models.services;

import com.apirest.lookapp.models.entity.Entidad;
import java.util.List;

public interface IEntidadService {

  public List<Entidad> findAll();

  public Entidad findById(Long id);

  public Entidad save(Entidad entidad);

  public void deleteById(Long id);
}

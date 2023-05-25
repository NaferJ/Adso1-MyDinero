package com.apirest.lookapp.models.services;

import com.apirest.lookapp.models.entity.Grupo;
import java.util.List;

public interface IGrupoService {
  public List<Grupo> findAll();

  public Grupo findById(Long id);

  public Grupo save(Grupo grupo);

  public void deleteById(Long id);
}

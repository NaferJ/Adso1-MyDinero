package com.apirest.lookapp.models.services;

import com.apirest.lookapp.models.entity.Productos;
import java.util.Collection;

public interface IProductoService {
  public Collection<Productos> findAll();

  public Productos findById(Long id);

  public Productos save(Productos producto);

  public void deleteById(Long id);
}

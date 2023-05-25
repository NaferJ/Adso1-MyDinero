package com.apirest.lookapp.models.services;

import com.apirest.lookapp.models.dao.IEntidadDao;
import com.apirest.lookapp.models.entity.Entidad;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class EntidadService implements IEntidadService{

  @Autowired
  private IEntidadDao entidadDao;
  
  public List<Entidad> findAll() {
    return entidadDao.findAll();
  }

  @Override
  public Entidad findById(Long id) {
    return entidadDao.findById(id).orElse(null);
  }

  @Override
  public Entidad save(Entidad entidad) {
    return entidadDao.save(entidad);
  }

  @Override
  public void deleteById(Long id) {
    entidadDao.deleteById(id);
  }
  
}

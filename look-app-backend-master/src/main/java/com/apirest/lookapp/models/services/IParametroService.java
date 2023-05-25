package com.apirest.lookapp.models.services;

import com.apirest.lookapp.models.entity.Parametro;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public interface IParametroService {
  public List<Parametro> findAll();

  public Parametro findById(Long id);

  public Parametro save(Parametro parametro);

  public void deleteById(Long id);
}

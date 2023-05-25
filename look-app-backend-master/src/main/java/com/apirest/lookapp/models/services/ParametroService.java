package com.apirest.lookapp.models.services;

import com.apirest.lookapp.models.dao.IParametroDao;
import com.apirest.lookapp.models.entity.Parametro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ParametroService implements IParametroService{

    @Autowired IParametroDao parametroDao;

    @Transactional
    @Override
    public List<Parametro> findAll() {
        return parametroDao.findAll();
    }

    @Transactional
    public List<Parametro> findByIdEntidad(Long idEntidad) {
        return parametroDao.findByIdEntidad(idEntidad);
    }

    @Override
    public Parametro findById(Long id) {
        return parametroDao.findById(id).orElse(null);
    }

    @Override
    public Parametro save(Parametro parametro) {
        return parametroDao.save(parametro);
    }

    @Override
    public void deleteById(Long id) {
        parametroDao.deleteById(id);
    }
  
}

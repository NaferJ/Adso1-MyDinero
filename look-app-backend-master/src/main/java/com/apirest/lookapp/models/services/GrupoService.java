package com.apirest.lookapp.models.services;

import com.apirest.lookapp.models.dao.IGrupoDao;
import com.apirest.lookapp.models.entity.Grupo;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GrupoService implements IGrupoService{

    @Autowired
    private IGrupoDao grupoDao;
    
    @Override
    public List<Grupo> findAll() {
        return grupoDao.findAll();
    }

    @Override
    public Grupo findById(Long id) {
        return grupoDao.findById(id).orElse(null);
    }

    @Override
    public Grupo save(Grupo grupo) {
        return grupoDao.save(grupo);
    }

    @Override
    public void deleteById(Long id) {
        grupoDao.deleteById(id);
    }
  
}

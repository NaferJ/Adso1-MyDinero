package com.apirest.lookapp.models.services;

import com.apirest.lookapp.models.entity.UnidadMedida;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.apirest.lookapp.models.dao.IUnidadMedidaDao;

@Service
public class UnidadMedidaService implements IUnidadMedidaService{

    @Autowired
    private IUnidadMedidaDao unidadMedidaDao;
    

    public List<UnidadMedida> findAll() {
        return unidadMedidaDao.findAll();
    }

    @Override
    public UnidadMedida findById(Long id) {
        return unidadMedidaDao.findById(id).orElse(null);
    }

    @Override
    public UnidadMedida save(UnidadMedida unidadMedida) {
        return unidadMedidaDao.save(unidadMedida);
    }

    @Override
    public void deleteById(Long id) {
        unidadMedidaDao.deleteById(id);
    }
  
}

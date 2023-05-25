package com.apirest.lookapp.models.services;

import com.apirest.lookapp.models.dao.IBienServicioDao;
import com.apirest.lookapp.models.entity.BienesServicios;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BienServicioService implements IBienServicioService{

    @Autowired
    private IBienServicioDao bienServicioDao;

    public List<BienesServicios> findAll() {
        return bienServicioDao.findAll();
    }

    @Override
    public BienesServicios findById(Long id) {
        return bienServicioDao.findById(id).orElse(null);
    }

    @Override
    public BienesServicios save(BienesServicios bienServicio) {
        return bienServicioDao.save(bienServicio);
    }

    @Override
    public void deleteById(Long id) {
        bienServicioDao.deleteById(id);
    }
  
}

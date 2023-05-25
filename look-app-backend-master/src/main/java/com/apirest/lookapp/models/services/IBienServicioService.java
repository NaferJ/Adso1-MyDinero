package com.apirest.lookapp.models.services;

import com.apirest.lookapp.models.entity.BienesServicios;
import java.util.List;

public interface IBienServicioService {

    public List<BienesServicios> findAll();

    public BienesServicios findById(Long id);

    public BienesServicios save(BienesServicios bienServicio);

    public void deleteById(Long id);
}

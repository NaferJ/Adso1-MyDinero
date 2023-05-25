package com.apirest.lookapp.models.services;

import java.util.List;

import com.apirest.lookapp.models.dao.IProductoDao;
import com.apirest.lookapp.models.entity.Productos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductoService implements IProductoService {
    
    @Autowired
    private IProductoDao productoDao;
    
    public List<Productos> findAll() {
        return productoDao.findAll();
    }

    @Override
    public Productos findById(Long id) {
        return productoDao.findById(id).orElse(null);
    }

    @Override
    public Productos save(Productos producto) {
        return productoDao.save(producto);
    }

    @Override
    public void deleteById(Long id) {
        productoDao.deleteById(id);
    }

    public List<Productos> findByIdEntidad(Long idEntidad) {
        return productoDao.findByIdEntidad(idEntidad);
    }
}

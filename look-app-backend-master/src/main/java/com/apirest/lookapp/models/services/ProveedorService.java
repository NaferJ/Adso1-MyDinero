package com.apirest.lookapp.models.services;

import com.apirest.lookapp.models.entity.Proveedores;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.apirest.lookapp.models.dao.IProveedorDao;

@Service
public class ProveedorService implements IProveedorService{

    @Autowired
    private IProveedorDao proveedorDao;
    
    public List<Proveedores> findAll() {
        return proveedorDao.findAll();
    }

    @Override
    public Proveedores findById(Long id) {
        return proveedorDao.findById(id).orElse(null);
    }

    @Override
    public Proveedores save(Proveedores proveedor) {
        return proveedorDao.save(proveedor);
    }

    @Override
    public void deleteById(Long id) {
        proveedorDao.deleteById(id);
    }
}

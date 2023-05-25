package com.apirest.lookapp.models.services;

import com.apirest.lookapp.models.entity.Proveedores;
import java.util.List;

public interface IProveedorService {

    public List<Proveedores> findAll();

    public Proveedores findById(Long id);

    public Proveedores save(Proveedores proveedor);

    public void deleteById(Long id);
}

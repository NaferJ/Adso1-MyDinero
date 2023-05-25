package com.apirest.lookapp.models.dao;

import java.util.List;

import com.apirest.lookapp.models.entity.Proveedores;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface IProveedorDao extends JpaRepository<Proveedores, Long>{
  @Query("SELECT p from Proveedores p WHERE p.estado = 1")
  public List<Proveedores> findAll();
}

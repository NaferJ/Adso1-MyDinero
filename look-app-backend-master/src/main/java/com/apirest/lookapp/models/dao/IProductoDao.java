package com.apirest.lookapp.models.dao;

import com.apirest.lookapp.models.entity.Productos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IProductoDao extends JpaRepository<Productos, Long>{

  @Query(value = "SELECT p from Productos p WHERE p.estado = 1")
  public List<Productos> findAll();

  @Query(value = "SELECT * FROM productos WHERE id_entidad=:idEntidad", nativeQuery = true)
  public List<Productos> findByIdEntidad(@Param("idEntidad") Long idEntidad);
  
}
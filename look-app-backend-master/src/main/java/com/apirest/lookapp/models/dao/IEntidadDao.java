package com.apirest.lookapp.models.dao;

import com.apirest.lookapp.models.entity.Entidad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IEntidadDao extends JpaRepository<Entidad, Long>{
 
  @Query("FROM Entidad e WHERE e.estado = 1")
  List<Entidad> findAll ();
}

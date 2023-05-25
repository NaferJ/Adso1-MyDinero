package com.apirest.lookapp.models.dao;

import com.apirest.lookapp.models.entity.Salidas;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ISalidaDao extends JpaRepository<Salidas, Long>{
  
}

package com.apirest.lookapp.models.dao;

import com.apirest.lookapp.models.entity.Grupo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IGrupoDao extends JpaRepository<Grupo, Long>{
  
}

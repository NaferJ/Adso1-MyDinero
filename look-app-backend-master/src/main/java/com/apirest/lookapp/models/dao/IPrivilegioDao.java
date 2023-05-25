package com.apirest.lookapp.models.dao;

import com.apirest.lookapp.models.entity.Privilegios;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPrivilegioDao extends JpaRepository<Privilegios, Long>{
  
}

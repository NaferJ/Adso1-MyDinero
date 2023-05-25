package com.apirest.lookapp.models.dao;

import com.apirest.lookapp.models.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IRoleDao extends JpaRepository<Role, Long>{
  
}
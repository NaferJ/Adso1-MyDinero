package com.apirest.lookapp.models.dao;

import com.apirest.lookapp.models.entity.UnidadMedida;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IUnidadMedidaDao extends JpaRepository<UnidadMedida, Long>{
    @Query("SELECT u From UnidadMedida u WHERE u.estado = 1")
    public List<UnidadMedida> findAll();
}

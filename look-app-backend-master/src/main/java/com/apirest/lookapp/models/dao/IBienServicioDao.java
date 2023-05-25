package com.apirest.lookapp.models.dao;

import com.apirest.lookapp.models.entity.BienesServicios;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IBienServicioDao extends JpaRepository<BienesServicios, Long>{

	@Query(value = "SELECT  b from BienesServicios  b where b.estado = 1")
	public List<BienesServicios> findAll();
}

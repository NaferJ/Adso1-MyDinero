package com.apirest.lookapp.models.dao;

import com.apirest.lookapp.models.entity.PuestosTrabajo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IPuestoTrabajoDao extends JpaRepository<PuestosTrabajo, Long>{


	@Query(value = "SELECT p From PuestosTrabajo p WHERE p.estado = 1")
	public List<PuestosTrabajo> findAll();
}

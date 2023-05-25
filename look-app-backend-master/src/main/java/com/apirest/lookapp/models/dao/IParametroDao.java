package com.apirest.lookapp.models.dao;

import com.apirest.lookapp.models.entity.Parametro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IParametroDao extends JpaRepository<Parametro, Long>{
	@Query (value = "SELECT * FROM parametro WHERE parametro.id_entidad = :idEntidad", nativeQuery = true)
	List<Parametro> findByIdEntidad (@Param("idEntidad") Long idEntidad);
}

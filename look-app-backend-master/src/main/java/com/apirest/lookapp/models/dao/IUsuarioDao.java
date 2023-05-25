package com.apirest.lookapp.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.apirest.lookapp.models.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUsuarioDao extends JpaRepository<Usuario, Long> {

  public Usuario findByUsername(String username);

  @Query("select u from Usuario u where u.username=?1")
  public Usuario findByUsername2(String username);

  @Query("select u from Usuario u")
  public List<Usuario> findAllUsers();

  @Query(value = "SELECT * FROM usuarios WHERE id_entidad = :idEntidad AND enabled = 1", nativeQuery = true)
  public List<Usuario> findByIdEntidad(@Param("idEntidad") Long idEntidad);

}
package com.apirest.lookapp.models.services;

import com.apirest.lookapp.models.entity.Usuario;

public interface IUsuarioService {
  
  public Usuario findByUsername(String username);
  
  public Usuario findById(Long id);
  
  public Usuario save(Usuario usuario);
}

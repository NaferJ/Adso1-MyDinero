package com.apirest.lookapp.auth;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.stereotype.Component;

import com.apirest.lookapp.models.entity.Usuario;
import com.apirest.lookapp.models.services.IUsuarioService;

@Component
public class InfoAdicionalToken implements TokenEnhancer {

  @Autowired
  private IUsuarioService usuarioService;

  @Override
  public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {

    Usuario usuario = usuarioService.findByUsername(authentication.getName());

    Map<String, Object> info = new HashMap<>();
    info.put("username", usuario.getUsername());
    info.put("nombre", usuario.getNombres());
    info.put("apellido", usuario.getApellidos());
    info.put("email", usuario.getEmail());
    info.put("entidad", usuario.getIdEntidad());
    info.put("enabled", usuario.getEnabled());
    info.put("id", usuario.getId());
    info.put("roles", usuario.getRoles());
    ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(info);
    return accessToken;
  }

}

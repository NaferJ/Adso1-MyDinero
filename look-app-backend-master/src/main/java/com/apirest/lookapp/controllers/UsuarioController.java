package com.apirest.lookapp.controllers;

import com.apirest.lookapp.models.entity.Usuario;
import com.apirest.lookapp.models.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@CrossOrigin(origins = {"*"})
@RestController
@RequestMapping("/api/v1")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/usuarios/list")
    public List<Usuario> findAll() {
        return usuarioService.findAllUsers();
    }

    @GetMapping("/usuario/{id}")
    public ResponseEntity<?> show(@PathVariable("id") Long id) {

        Usuario usuario;

        Map<String, Object> response = new HashMap<>();

        try {
            usuario = usuarioService.findById(id);
        } catch (DataAccessException e) {
            response.put("mensaje", "Error al realizar la consulta");
            response.put("error", e.getMessage() + ": " + (e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if (usuario == null) {
            response.put("mensaje", "El usuario con el ID:".concat(id.toString().concat(" no existe")));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<Usuario>(usuario, HttpStatus.OK);
    }

    @PutMapping("/usuario/update/{id}")
    public ResponseEntity<?> update(@RequestBody Usuario usuario, @PathVariable("id") Long id) {

        Usuario usuarioActual = usuarioService.findById(id);
        Usuario usuarioUpdt;

        Map<String, Object> response = new HashMap<>();

        if (usuarioActual == null) {
            response.put("mensaje", "Eror: no se puede editar, el usuario con el ID: " + id + " No existe");
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }

        try {
            usuarioActual.setNombres(usuario.getNombres());
            usuarioActual.setApellidos(usuario.getNombres());
            usuarioActual.setDireccion(usuario.getDireccion());
            usuarioActual.setEmail(usuario.getEmail());
            usuarioActual.setFecha_nacimiento(usuario.getFecha_nacimiento());
            usuarioUpdt = usuarioService.save(usuarioActual);
        } catch (DataAccessException e) {
            response.put("mensaje", "Error al actualizar el usuario");
            response.put("error", e.getMessage() + ": " + (e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("mensaje", "El usuario ha sido actualizado con éxito!");
        response.put("usuario", usuarioUpdt);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/usuarios/entidad/{idEntidad}")
    public List<Usuario> findByIdEntidad(@PathVariable("idEntidad") Long idEntidad) {
        return usuarioService.findByIdEntidad(idEntidad);
    }

    @PostMapping("/user/new")
    public ResponseEntity<?> create(@RequestBody Usuario usuario) {

        Usuario usuarioNew;

        Map<String, Object> response = new HashMap<>();

        try {

            usuarioNew = usuarioService.save(usuario);

        } catch (DataAccessException e) {
            response.put("mensaje", "Error al crear el usuario");
            response.put("error", e.getMessage() + ": " + (e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("mensaje", "El usuario ha sido creado con éxito!");
        response.put("usuario", usuarioNew);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/usuario/delete/{id}")
    public ResponseEntity<?> delete(@RequestBody Usuario usuario, @PathVariable("id") Long id) {

        Usuario usuarioActual = usuarioService.findById(id);
        Usuario usuarioUpdt;

        Map<String, Object> response = new HashMap<>();

        if (usuarioActual == null) {
            response.put("mensaje", "Eror: no se puede eliminar, el usuario con el ID: " + id + " No existe");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }

        try {

            usuarioActual.setEnabled(usuario.getEnabled());

            usuarioUpdt = usuarioService.save(usuarioActual);

        } catch (DataAccessException e) {
            response.put("mensaje", "Error al eliminar el usuario");
            response.put("error", e.getMessage() + ": " + (e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("mensaje", "El usuario ha sido eliminado con éxito!");
        response.put("usuario", usuarioUpdt);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PostMapping("/usuario/upload")
    public ResponseEntity<?> upload(@RequestParam("archivo") MultipartFile archivo, @RequestParam("id") Long id) {

        Map<String, Object> response = new HashMap<>();

        Usuario usuario = usuarioService.findById(id);

        if (!archivo.isEmpty()) {
            String nombreArchivo = UUID.randomUUID().toString() + "_" + archivo.getOriginalFilename().replace(" ", "");
            Path rutaArchivo = Paths.get("uploads").resolve(nombreArchivo).toAbsolutePath();
            try {
                Files.copy(archivo.getInputStream(), rutaArchivo);
            } catch (IOException e) {
                response.put("mensaje", "Error al subir la imagen " + nombreArchivo);
                response.put("error", e.getMessage().concat(": ").concat(e.getCause().getMessage()));
                return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }

            String nombreImagenAnterior = usuario.getImagen();

            if (nombreImagenAnterior != null && nombreImagenAnterior.length() > 0) {
                Path rutaLogoAnterior = Paths.get("uploads").resolve(nombreImagenAnterior).toAbsolutePath();
                File archivoLogoAnterior = rutaLogoAnterior.toFile();
                if (archivoLogoAnterior.exists() && archivoLogoAnterior.canRead()) {
                    archivoLogoAnterior.delete();
                }
            }

            usuario.setImagen(nombreArchivo);

            usuarioService.save(usuario);

            response.put("usuario", usuario);
            response.put("mensaje", "Has subido correctamente la imagen: " + nombreArchivo);
        }

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}

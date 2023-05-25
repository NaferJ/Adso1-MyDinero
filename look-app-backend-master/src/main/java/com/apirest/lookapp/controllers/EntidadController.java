package com.apirest.lookapp.controllers;

import com.apirest.lookapp.models.entity.Entidad;
import com.apirest.lookapp.models.services.EntidadService;
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
public class EntidadController {

    @Autowired
    private EntidadService entidadService;

    @GetMapping("/entidad/list")
    public List<Entidad> findAll() {
        return entidadService.findAll();
    }

    @GetMapping("/entidad/{id}")
    public ResponseEntity<?> show(@PathVariable Long id) {
        Entidad entidad;
        Map<String, Object> response = new HashMap<>();

        try {
            entidad = entidadService.findById(id);
        } catch (DataAccessException e) {
            response.put("mensaje", "Error al realizar la consulta");
            response.put("error", e.getMessage() + ": " + (e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if (entidad == null) {
            response.put("mensaje", "La entidad con el ID:".concat(id.toString().concat(" no existe")));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Entidad>(entidad, HttpStatus.OK);
    }

    @PostMapping("/entidad/new")
    public ResponseEntity<?> create(@RequestBody Entidad entidad) {
        Entidad entidadNew;
        Map<String, Object> response = new HashMap<>();

        try {
            entidadNew = entidadService.save(entidad);
        } catch (DataAccessException e) {
            response.put("mensaje", "Error al realizar la inserción");
            response.put("error", e.getMessage() + ": " + (e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("entidad", entidadNew);
        response.put("mensaje", "La entidad ha sido creada con éxito!");
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
    }

    @PutMapping("/entidad/update/{id}")
    public ResponseEntity<?> update(@RequestBody Entidad entidad, @PathVariable Long id) {

        Entidad entidadActual = entidadService.findById(id);
        Entidad entidadUpdated;

        Map<String, Object> response = new HashMap<>();

        if (entidadActual == null) {
            response.put("mensaje", "Eror: no se puede editar, la entidad con el ID: ".concat(id.toString().concat(". No existe")));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }
        try {
            entidadActual.setNombre(entidad.getNombre());
            entidadActual.setNit(entidad.getNit());
            entidadActual.setDireccion(entidad.getDireccion());
            entidadActual.setTelefono(entidad.getTelefono());
            entidadActual.setContactNombre(entidad.getContactNombre());
            entidadActual.setContactTelefono(entidad.getContactTelefono());
            entidadActual.setContactEmail(entidad.getContactEmail());
            entidadActual.setLogoNombre(entidad.getLogoNombre());
            entidadActual.setEstado(entidad.getEstado());
            entidadActual.setCiudad(entidad.getCiudad());
            entidadActual.setPais(entidad.getPais());
            entidadActual.setCodigoZip(entidad.getCodigoZip());
            entidadActual.setDescripcion(entidad.getDescripcion());

            entidadUpdated = entidadService.save(entidadActual);

        } catch (DataAccessException e) {

            response.put("mensaje", "Error al actualizar la entidad");
            response.put("error", e.getMessage() + ": " + (e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);

        }

        response.put("mensaje", "La entidad ha sido actualizada con éxito!");
        response.put("entidad", entidadUpdated);

        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
    }

    @PutMapping("/entidad/delete/{id}")
    public ResponseEntity<?> delete(@RequestBody Entidad entidad, @PathVariable Long id) {

        Entidad entidadActual = entidadService.findById(id);
        Entidad entidadUpdated;

        Map<String, Object> response = new HashMap<>();

        if (entidadActual == null) {
            response.put("mensaje", "Eror: no se puede eliminar, la entidad con el ID: ".concat(id.toString().concat(". No existe")));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }
        try {

            entidadActual.setEstado(entidad.getEstado());

            entidadUpdated = entidadService.save(entidadActual);

        } catch (DataAccessException e) {

            response.put("mensaje", "Error al eliminar la entidad");
            response.put("error", e.getMessage() + ": " + (e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);

        }

        response.put("mensaje", "La entidad ha sido Eliminada con éxito!");
        response.put("entidad", entidadUpdated);

        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
    }


    @PostMapping("/entidad/upload")
    public ResponseEntity<?> upload(@RequestParam("archivo") MultipartFile archivo, @RequestParam("id") Long id) {

        Map<String, Object> response = new HashMap<>();

        Entidad entidad = entidadService.findById(id);

        if (!archivo.isEmpty()) {
            String nombreArchivo = UUID.randomUUID().toString() + "_" + archivo.getOriginalFilename().replace(" ", "");
            Path rutaArchivo = Paths.get("uploads").resolve(nombreArchivo).toAbsolutePath();
            try {
                Files.copy(archivo.getInputStream(), rutaArchivo);
            } catch (IOException e) {
                response.put("mensaje", "Error al subir la imagen " + nombreArchivo);
                response.put("error", e.getMessage().concat(": ").concat(e.getCause().getMessage()));
                return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }

            String nombreLogoAnterior = entidad.getLogoNombre();

            if (nombreLogoAnterior != null && nombreLogoAnterior.length() > 0) {
                Path rutaLogoAnterior = Paths.get("uploads").resolve(nombreLogoAnterior).toAbsolutePath();
                File archivoLogoAnterior = rutaLogoAnterior.toFile();
                if (archivoLogoAnterior.exists() && archivoLogoAnterior.canRead()) {
                    archivoLogoAnterior.delete();
                }
            }

            entidad.setLogoNombre(nombreArchivo);

            entidadService.save(entidad);

            response.put("entidad", entidad);
            response.put("mensaje", "Has subido correctamente la imagen: " + nombreArchivo);
        }

        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
    }

}

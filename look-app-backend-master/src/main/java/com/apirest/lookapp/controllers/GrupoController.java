package com.apirest.lookapp.controllers;

import com.apirest.lookapp.models.entity.Grupo;
import com.apirest.lookapp.models.services.GrupoService;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = {"*"})
@RestController
@RequestMapping("/api/v1")
public class GrupoController {

    @Autowired
    private GrupoService grupoService;

    /**
     *
     * @return Retorna todos los grupos
     */
    @GetMapping("/grupos/list")
    public List<Grupo> findAll() {
        return grupoService.findAll();
    }

    /**
     * @param id
     * @return Retorna un grupo por id..
     */
    @GetMapping("/grupo/{id}")
    public ResponseEntity<?> findById(@PathVariable("id") Long id) {
        Grupo grupo;

        Map<String, Object> response = new HashMap<>();

        try {
            grupo = grupoService.findById(id);
        } catch (DataAccessException e) {
            response.put("mensaje", "Error al realizar la consulta");
            response.put("error", e.getMessage()+": "+(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if (grupo == null) {
            response.put("mensaje", "El grupo con el ID:".concat(id.toString().concat(" no existe")));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Grupo>(grupo, HttpStatus.OK);
    }

    /**
     *
     * @param grupo
     * @return el grupo Creado
     */
    @PostMapping("/grupo/new")
    public ResponseEntity<?> create(@RequestBody Grupo grupo) {

        Grupo grupoNew;
        Map<String, Object> response = new HashMap<>();

        try {
            grupoNew = grupoService.save(grupo);
        } catch (DataAccessException e) {
            response.put("mensaje", "Error al realizar la inserción");
            response.put("error", e.getMessage()+": "+(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("mensaje", "El Grupo ha sido creado con éxito!");
        response.put("Grupo", grupoNew);
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
    }

    /**
     *
     * @param grupo
     * @param id
     * @return El grupo actualizado con su nueva info
     */
    @PutMapping("/grupo/update/{id}")
    public ResponseEntity<?> update(@RequestBody Grupo grupo, @PathVariable Long id) {

        Grupo grupoActual = grupoService.findById(id);
        Grupo grupoUpdated;

        Map<String, Object> response = new HashMap<>();

        if (grupoActual == null) {
            response.put("mensaje", "Eror: no se puede editar, el grupo con el ID: ".concat(id.toString().concat(". No existe")));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }
        try {
            grupoActual.setNombre(grupo.getNombre());
            grupoUpdated = grupoService.save(grupoActual);

        } catch (DataAccessException e) {

            response.put("mensaje", "Error al actualizar el grupo");
            response.put("error", e.getMessage()+": "+(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);

        }

        response.put("mensaje", "El grupo ha sido actualizado con éxito!");
        response.put("grupo", grupoUpdated);

        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
    }

    @DeleteMapping("/grupo/delete/{id}")
    public ResponseEntity<?> deleteById(@PathVariable("id") Long id) {

        Map<String, Object> response = new HashMap<>();
        try {
            grupoService.deleteById(id);
        } catch (DataAccessException e) {
            response.put("mensaje", "Error al Eliminar el grupo");
            response.put("error", e.getMessage()+": "+(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("mensaje", "El grupo ha sido eliminado con éxito!");
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
    }
}

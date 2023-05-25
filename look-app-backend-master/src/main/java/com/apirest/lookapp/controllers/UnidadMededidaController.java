package com.apirest.lookapp.controllers;

import com.apirest.lookapp.models.entity.UnidadMedida;
import com.apirest.lookapp.models.services.UnidadMedidaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = { "*" })
@RestController
@RequestMapping("/api/v1")
public class UnidadMededidaController {
  
    @Autowired
    private UnidadMedidaService unidadService;
    
    @GetMapping("/unidades/list")
    public List<UnidadMedida> findAll()  {
        return unidadService.findAll();
    }

    @GetMapping("/unidad/{id}")
    public ResponseEntity<?> show(@PathVariable Long id){

        UnidadMedida um;

        Map<String, Object> response = new HashMap<>();

        try {
            um = unidadService.findById(id);
        } catch (DataAccessException e) {
            response.put("mensaje", "Error al realizar la consulta");
            response.put("error", e.getMessage()+": "+(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity <Map <String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if (um == null) {
            response.put("mensaje", "la unidad de medida con el ID:".concat(id.toString().concat(" no existe")));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity <UnidadMedida>(um, HttpStatus.OK);
    }

    @PostMapping("/unidad/new")
    public ResponseEntity<?> create(@RequestBody UnidadMedida um){

        UnidadMedida umNew;

        Map<String, Object> response = new HashMap<>();

        try {
            umNew = unidadService.save(um);
        } catch (DataAccessException e) {
            response.put("mensaje", "Error al realizar la inserción");
            response.put("error", e.getMessage()+": "+(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("mensaje", "La unidad de medida ha sido creado con éxito!");
        response.put("unidad", umNew);
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
    }

    @PutMapping("/unidad/update/{id}")
    public ResponseEntity<?> update(@RequestBody UnidadMedida um, @PathVariable Long id) {

        UnidadMedida umActual = unidadService.findById(id);
        UnidadMedida umUpdated;

        Map<String, Object> response = new HashMap<>();

        if (umActual == null) {
            response.put("mensaje", "Eror: no se puede editar, el proveedor con el ID: ".concat(id.toString().concat(". No existe")));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }
        try {
            umActual.setNombre(um.getNombre());
            umActual.setDescripcion(um.getDescripcion());

            umUpdated = unidadService.save(umActual);

        } catch (DataAccessException e) {

            response.put("mensaje", "Error al actualizar la unidad de medida");
            response.put("error", e.getMessage()+": "+(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);

        }

        response.put("mensaje", "la unidad de medida ha sido actualizada con éxito!");
        response.put("unidad", umUpdated);

        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
    }

    @PutMapping("/unidad/delete/{id}")
    public ResponseEntity<?> delete(@RequestBody UnidadMedida um, @PathVariable Long id) {

        UnidadMedida umActual = unidadService.findById(id);
        UnidadMedida umUpdated;

        Map<String, Object> response = new HashMap<>();

        if (umActual == null) {
            response.put("mensaje", "Eror: no se puede eliminar, la unidad con el ID: ".concat(id.toString().concat(". No existe")));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }
        try {

            umActual.setEstado(um.getEstado());

            umUpdated = unidadService.save(umActual);

        } catch (DataAccessException e) {

            response.put("mensaje", "Error al eliminar la unidad de medida");
            response.put("error", e.getMessage()+": "+(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);

        }

        response.put("mensaje", "Unidad de medida eliminada con éxito!");
        response.put("unidad", umUpdated);

        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
    }
}

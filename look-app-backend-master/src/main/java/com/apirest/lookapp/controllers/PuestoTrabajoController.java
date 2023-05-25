package com.apirest.lookapp.controllers;

import com.apirest.lookapp.models.entity.PuestosTrabajo;
import com.apirest.lookapp.models.services.PuestoTrabajoService;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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
public class PuestoTrabajoController {

    @Autowired
    private PuestoTrabajoService puestoTrabajoService;

    @GetMapping("/puestos/list")
    public List<PuestosTrabajo> findAll() {
        return puestoTrabajoService.findAll();
    }

    @GetMapping("/puesto/{id}")
    public ResponseEntity<?> show(@PathVariable Long id) {
        PuestosTrabajo puesto;
        Map<String, Object> response = new HashMap<>();

        try {
            puesto = puestoTrabajoService.findById(id);
        } catch (DataAccessException e) {
            response.put("mensaje", "Error al realizar la consulta");
            response.put("error", e.getMessage()+": "+(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if (puesto == null) {
            response.put("mensaje", "El puesto de trabajo con el ID:".concat(id.toString().concat(" no existe")));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<PuestosTrabajo>(puesto, HttpStatus.OK);
    }

    @PostMapping("/puesto/new")
    public ResponseEntity<?> create(@RequestBody PuestosTrabajo puesto) {

        PuestosTrabajo puestoNew;
        Map<String, Object> response = new HashMap<>();

        try {
            puestoNew = puestoTrabajoService.save(puesto);
        } catch (DataAccessException e) {
            response.put("mensaje", "Error al realizar la inserción");
            response.put("error", e.getMessage()+": "+(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("mensaje", "El puesto ha sido creado con éxito!");
        response.put("Puesto", puestoNew);
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
    }

    @PutMapping("/puesto/update/{id}")
    public ResponseEntity<?> update(@RequestBody PuestosTrabajo puesto, @PathVariable Long id) {

        PuestosTrabajo puestoActual = puestoTrabajoService.findById(id);
        PuestosTrabajo puestoUpdated;

        Map<String, Object> response = new HashMap<>();

        if (puestoActual == null) {
            response.put("mensaje", "Eror: no se puede editar, el puesto con el ID: ".concat(id.toString().concat(". No existe")));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }
        try {
            puestoActual.setNombre(puesto.getNombre());
            puestoActual.setDescripcion(puesto.getDescripcion());
            puestoActual.setUtilidad(puesto.getUtilidad());

            puestoUpdated = puestoTrabajoService.save(puestoActual);
        } catch (DataAccessException e) {
            response.put("mensaje", "Error al actualizar el puesto");
            response.put("error", e.getMessage()+": "+(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("mensaje", "El puesto ha sido actualizado con éxito!");
        response.put("Producto", puestoUpdated);

        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
    }

    @PutMapping("/puesto/delete/{id}")
    public ResponseEntity<?> delete(@RequestBody PuestosTrabajo puesto, @PathVariable Long id) {

        PuestosTrabajo puestoActual = puestoTrabajoService.findById(id);
        PuestosTrabajo puestoUpdated;

        Map<String, Object> response = new HashMap<>();

        if (puestoActual == null) {
            response.put("mensaje", "Eror: no se puede eliminar, el puesto con el ID: ".concat(id.toString().concat(". No existe")));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }
        try {

            puestoActual.setEstado(puesto.isEstado());

            puestoUpdated = puestoTrabajoService.save(puestoActual);

        } catch (DataAccessException e) {

            response.put("mensaje", "Error al eliminar el puesto");
            response.put("error", e.getMessage()+": "+(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);

        }

        response.put("mensaje", "El puesto ha sido Eliminado con éxito!");
        response.put("puesto", puestoUpdated);

        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
    }

    @PutMapping("/puesto/active/{id}")
    public ResponseEntity<?> active(@RequestBody PuestosTrabajo puesto, @PathVariable Long id) {

        PuestosTrabajo puestoActual = puestoTrabajoService.findById(id);
        PuestosTrabajo puestoUpdated;

        Map<String, Object> response = new HashMap<>();

        if (puestoActual == null) {
            response.put("mensaje", "Eror: no se puede activar, el puesto con el ID: ".concat(id.toString().concat(". No existe")));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }
        try {

            puestoActual.setEstado(puesto.isEstado());

            puestoUpdated = puestoTrabajoService.save(puestoActual);

        } catch (DataAccessException e) {

            response.put("mensaje", "Error al activar el puesto");
            response.put("error", e.getMessage()+": "+(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);

        }

        response.put("mensaje", "El puesto ha sido activado con éxito!");
        response.put("puesto", puestoUpdated);

        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
    }
}

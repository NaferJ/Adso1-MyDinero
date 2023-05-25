package com.apirest.lookapp.controllers;

import com.apirest.lookapp.models.entity.BienesServicios;
import com.apirest.lookapp.models.services.BienServicioService;
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

@CrossOrigin(origins = { "*" })
@RestController
@RequestMapping("/api/v1")
public class BienServicioController {
    
    @Autowired
    private BienServicioService bienService;
    
    @GetMapping("/bienes/list")
    public List<BienesServicios> findAll(){
        return bienService.findAll();
    }
    
    @GetMapping("/bien/{id}")
    public ResponseEntity<?> show(@PathVariable Long id) {
        BienesServicios bien;
        Map<String, Object> response = new HashMap<>();
        
        try {
            bien = bienService.findById(id);
        } catch (DataAccessException e) {
            response.put("mensaje", "Error al realizar la consulta");
            response.put("error", e.getMessage()+": "+(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if (bien == null) {
            response.put("mensaje", "El Bien o servicio con el ID:".concat(id.toString().concat(" no existe")));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<BienesServicios>(bien, HttpStatus.OK);
    }
    
    @PostMapping("/bienservicio/new")
    public ResponseEntity<?> create(@RequestBody BienesServicios bienservicio) {

        BienesServicios bienservicioNew;
        Map<String, Object> response = new HashMap<>();

        try {
            bienservicioNew = bienService.save(bienservicio);
        } catch (DataAccessException e) {
            response.put("mensaje", "Error al realizar la inserción");
            response.put("error", e.getMessage()+": "+(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("mensaje", "El Bien o Servicio ha sido creado con éxito!");
        response.put("Bien_Servicio", bienservicioNew);
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
    }
    
    @PutMapping("/bienservicio/update/{id}")
    public ResponseEntity<?> update(@RequestBody BienesServicios bienservicio, @PathVariable Long id) {

        BienesServicios bienservicioActual = bienService.findById(id);
        BienesServicios bienservicioUpdated;

        Map<String, Object> response = new HashMap<>();

        if (bienservicioActual == null) {
            response.put("mensaje", "Eror: no se puede editar, el bien servicio con el ID: ".concat(id.toString().concat(". No existe")));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }
        try {
            bienservicioActual.setNombres(bienservicio.getNombres());
            bienservicioActual.setValor(bienservicio.getValor());
            bienservicioActual.setEstado(bienservicio.getEstado());
            
            bienservicioUpdated = bienService.save(bienservicioActual);
        } catch (DataAccessException e) {

            response.put("mensaje", "Error al actualizar el bien o servicio");
            response.put("error", e.getMessage()+": "+(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);

        }

        response.put("mensaje", "El bien o servicio ha sido actualizado con éxito!");
        response.put("Producto", bienservicioUpdated);

        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
    }
    
    @PutMapping("/bienservicio/delete/{id}")
    public ResponseEntity<?> delete(@RequestBody BienesServicios bienservicio, @PathVariable Long id) {

        BienesServicios bienservicioActual = bienService.findById(id);
        BienesServicios bienservicioUpdated ;

        Map<String, Object> response = new HashMap<>();

        if (bienservicioActual == null) {
            response.put("mensaje", "Eror: no se puede eliminar, el producto con el ID: ".concat(id.toString().concat(". No existe")));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }
        try {

            bienservicioActual.setEstado(bienservicio.getEstado());

            bienservicioUpdated = bienService.save(bienservicioActual);

        } catch (DataAccessException e) {

            response.put("mensaje", "Error al eliminar el Bien o Servicio");
            response.put("error", e.getMessage()+": "+(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);

        }

        response.put("mensaje", "El bien o servicio ha sido Eliminado con éxito!");
        response.put("Bien_Servicio", bienservicioUpdated);

        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
    }

    @PutMapping("/bienservicio/active/{id}")
    public ResponseEntity<?> active(@RequestBody BienesServicios bien, @PathVariable Long id) {

        BienesServicios bienActual = bienService.findById(id);
        BienesServicios bienUpdated;

        Map<String, Object> response = new HashMap<>();

        if (bienActual == null) {
            response.put("mensaje", "Eror: no se puede activar, el bien con el ID: ".concat(id.toString().concat(". No existe")));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }
        try {

            bienActual.setEstado(bien.getEstado());

            bienUpdated = bienService.save(bienActual);

        } catch (DataAccessException e) {

            response.put("mensaje", "Error al activar el bien");
            response.put("error", e.getMessage()+": "+(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);

        }

        response.put("mensaje", "El puesto ha sido activado con éxito!");
        response.put("bien", bienUpdated);

        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
    }
}

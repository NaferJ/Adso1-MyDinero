package com.apirest.lookapp.controllers;

import com.apirest.lookapp.models.entity.Entidad;
import com.apirest.lookapp.models.entity.Parametro;
import com.apirest.lookapp.models.services.EntidadService;
import com.apirest.lookapp.models.services.ParametroService;
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
public class ParametroController {

    @Autowired
    private EntidadService entidadService;

    @Autowired
    private ParametroService parametroServ;
    
    @GetMapping("/parametros/list")
    public List<Parametro> findAll(){
        return parametroServ.findAll();
    }
    
    @GetMapping("/parametro/{id}")
    public ResponseEntity<?> show(@PathVariable("id") Long id){
        
        Parametro parametro;
        
        Map<String, Object> response = new HashMap<>();
        
        try {
            parametro = parametroServ.findById(id);
        } catch (DataAccessException e) {
            response.put("mensaje", "Error al realizar la consulta");
            response.put("error", e.getMessage()+": "+(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if (parametro == null) {
            response.put("mensaje", "El parámetro con el ID:".concat(id.toString().concat(" no existe")));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Parametro>(parametro, HttpStatus.OK);
    }
    
    @PutMapping("/parametro/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") Long id, @RequestBody Parametro parametro){
        
        Parametro parametroActual = parametroServ.findById(id);
        Parametro parametroUpdated;
        
        Map<String, Object> response = new HashMap<>();
        
        if (parametroActual == null) {
            response.put("mensaje", "Eror: no se puede editar, el parametro con el ID: ".concat(id.toString().concat(". No existe")));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }
        try {
            parametroActual.setNombre(parametro.getNombre());
            parametroActual.setAbrev(parametro.getAbrev());
            parametroActual.setFechaInicio(parametro.getFechaInicio());
            parametroActual.setFechaFin(parametro.getFechaFin());
            parametroActual.setCodigo(parametro.getCodigo());
            parametroActual.setValor(parametro.getValor());
            parametroUpdated = parametroServ.save(parametroActual);

        } catch (DataAccessException e) {

            response.put("mensaje", "Error al actualizar el parametro");
            response.put("error", e.getMessage()+": "+(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        
        response.put("mensaje", "El parametro ha sido actualizado con éxito!");
        response.put("Producto", parametroUpdated);

        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
    }
    
    @DeleteMapping("/parametro/delete/{id}")
    public ResponseEntity<?> deleteById(@PathVariable("id")Long id){
        
        Map<String, Object> response = new HashMap<>();
        try {
            parametroServ.deleteById(id);
        } catch (DataAccessException e) {
            response.put("mensaje", "Error al Eliminar el parametro");
            response.put("error", e.getMessage()+": "+(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        
        response.put("mensaje", "El parametro ha sido eliminado con éxito!");
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
    }

    @GetMapping ( "/parametros/entidad/{idEntidad}" )
    public List < Parametro > findByIdEntidad ( @PathVariable ( "idEntidad" ) Long idEntidad ) {
        Entidad entidad = entidadService.findById(idEntidad);
        Long id = entidad.getId();

        System.out.println(id.toString());

        return parametroServ.findByIdEntidad(id);
    }
}

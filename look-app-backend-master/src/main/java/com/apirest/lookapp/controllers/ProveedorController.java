package com.apirest.lookapp.controllers;

import com.apirest.lookapp.models.entity.Proveedores;
import com.apirest.lookapp.models.services.ProveedorService;
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
public class ProveedorController {
  
    @Autowired
    private ProveedorService proveedorService;
    
    @GetMapping("/proveedores/list")
    public List<Proveedores> findAll(){
        return proveedorService.findAll();
    }
    
    @GetMapping("/proveedor/{id}")
    public ResponseEntity<?> show(@PathVariable Long id){
        
        Proveedores proveedor;
        
        Map<String, Object> response = new HashMap<>();
        
        try {
            proveedor = proveedorService.findById(id);
        } catch (DataAccessException e) {
            response.put("mensaje", "Error al realizar la consulta");
            response.put("error", e.getMessage()+": "+(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if (proveedor == null) {
            response.put("mensaje", "El Proveedor con el ID:".concat(id.toString().concat(" no existe")));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Proveedores>(proveedor, HttpStatus.OK);
    }
    
    @PostMapping("/proveedor/new")
    public ResponseEntity<?> create(@RequestBody Proveedores proveedor){
        
        Proveedores proveedorNew;
        
        Map<String, Object> response = new HashMap<>();

        try {
            proveedorNew = proveedorService.save(proveedor);
        } catch (DataAccessException e) {
            response.put("mensaje", "Error al realizar la inserción");
            response.put("error", e.getMessage()+": "+(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("mensaje", "El proveedor ha sido creado con éxito!");
        response.put("Proveedor", proveedorNew);
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
    }
    
    @PutMapping("/proveedor/update/{id}")
    public ResponseEntity<?> update(@RequestBody Proveedores proveedor, @PathVariable Long id) {

        Proveedores proveedorActual = proveedorService.findById(id);
        Proveedores proveedorUpdated;

        Map<String, Object> response = new HashMap<>();

        if (proveedorActual == null) {
            response.put("mensaje", "Eror: no se puede editar, el proveedor con el ID: ".concat(id.toString().concat(". No existe")));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }
        try {
            
            proveedorActual.setNit(proveedor.getNit());
            proveedorActual.setNombre(proveedor.getNombre());
            proveedorActual.setEstado(proveedor.getEstado());
            proveedorActual.setDireccion(proveedor.getDireccion());
            proveedorActual.setTelefono(proveedor.getTelefono());
            proveedorActual.setEmail(proveedor.getEmail());
            
            proveedorUpdated = proveedorService.save(proveedorActual);

        } catch (DataAccessException e) {

            response.put("mensaje", "Error al actualizar el proveedor");
            response.put("error", e.getMessage()+": "+(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);

        }

        response.put("mensaje", "El proveedor ha sido actualizado con éxito!");
        response.put("Proveedor", proveedorUpdated);

        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
    }
    
    @PutMapping("/proveedor/delete/{id}")
    public ResponseEntity<?> delete(@RequestBody Proveedores proveedor, @PathVariable Long id) {

        Proveedores proveedorActual = proveedorService.findById(id);
        Proveedores proveedorUpdated;

        Map<String, Object> response = new HashMap<>();

        if (proveedorActual == null) {
            response.put("mensaje", "Eror: no se puede eliminar, el proveedor con el ID: ".concat(id.toString().concat(". No existe")));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }
        try {

            proveedorActual.setEstado(proveedor.getEstado());

            proveedorUpdated = proveedorService.save(proveedorActual);

        } catch (DataAccessException e) {

            response.put("mensaje", "Error al eliminar el proveedor");
            response.put("error", e.getMessage()+": "+(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);

        }

        response.put("mensaje", "El proveedor ha sido eliminado con éxito!");
        response.put("Proveedor", proveedorUpdated);

        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
    }
}

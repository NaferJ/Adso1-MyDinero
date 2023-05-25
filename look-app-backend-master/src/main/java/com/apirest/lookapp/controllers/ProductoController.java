package com.apirest.lookapp.controllers;

import com.apirest.lookapp.models.entity.Productos;
import com.apirest.lookapp.models.entity.Proveedores;
import com.apirest.lookapp.models.entity.UnidadMedida;
import com.apirest.lookapp.models.services.ProductoService;
import com.apirest.lookapp.models.services.ProveedorService;
import com.apirest.lookapp.models.services.UnidadMedidaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

@CrossOrigin(origins = {"*"})
@RestController
@RequestMapping("/api/v1")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    @Autowired
    private ProveedorService proveedorServ;

    @Autowired
    private UnidadMedidaService unidadMedidadServ;

    @GetMapping("/productos/list")
    public Collection<Productos> findAll() {
        return productoService.findAll();
    }

		@GetMapping("/productos/entidad/{idEntidad}")
		public List<Productos> findByIdEntidad(@PathVariable("idEntidad") Long idEntidad){
			return productoService.findByIdEntidad(idEntidad);
		}

    @GetMapping("/producto/{id}")
    public ResponseEntity<?> show(@PathVariable Long id) {
        Productos producto ;
        Map<String, Object> response = new HashMap<>();

        try {
            producto = productoService.findById(id);
        } catch (DataAccessException e) {
            response.put("mensaje", "Error al realizar la consulta");
            response.put("error", e.getMessage()+": "+(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if (producto == null) {
            response.put("mensaje", "El Producto con el ID:".concat(id.toString().concat(" no existe")));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Productos>(producto, HttpStatus.OK);
    }

    @PostMapping("/producto/new")
    public ResponseEntity<?> create(@RequestBody Productos producto) {

        Map<String, Object> response = new HashMap<>();

        try {
            response.put("Producto", productoService.save(producto));
        } catch (DataAccessException e) {
            response.put("mensaje", "Error al realizar la inserción");
            response.put("error", e.getMessage()+": "+(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("mensaje", "El producto ha sido creado con éxito!");

        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
    }
    
    @PutMapping("/producto/update/{id}")
    public ResponseEntity<?> update(@RequestBody Productos producto, @PathVariable Long id) {

        Productos productoActual = productoService.findById(id);
        Productos productoUpdated;

        Map<String, Object> response = new HashMap<>();

        if (productoActual == null) {
            response.put("mensaje", "Eror: no se puede editar, el producto con el ID: ".concat(id.toString().concat(". No existe")));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }
        try {
            productoActual.setNombre(producto.getNombre());
            productoActual.setCantidad(producto.getCantidad());
            productoActual.setStockMin(producto.getStockMin());
            productoActual.setCodigoBar(producto.getCodigoBar());
            productoActual.setUbicacion(producto.getUbicacion());
            productoActual.setUtilidad(producto.getUtilidad());
            productoActual.setEstado(producto.getEstado());
            productoActual.setNoFactura(producto.getNoFactura());
            productoActual.setPrecioCompra(producto.getPrecioCompra());
            productoActual.setPrecioVenta(producto.getPrecioVenta());
            productoActual.setUtilidadEsperada(producto.getUtilidadEsperada());
            productoActual.setDescripcion(producto.getDescripcion());
            
            Collection<Proveedores> proveedores = new ArrayList<Proveedores>();
            producto.getProveedores().forEach(p -> proveedores.add(proveedorServ.findById(p.getId())));

            Collection<UnidadMedida> medidas = new ArrayList<UnidadMedida>();
            producto.getUnidadMedida().forEach(um -> medidas.add(unidadMedidadServ.findById(um.getId())));

            productoActual.setProveedores(proveedores);
            productoActual.setUnidadMedida(medidas);
            
            productoUpdated = productoService.save(productoActual);

        } catch (DataAccessException e) {

            response.put("mensaje", "Error al actualizar el producto");
            response.put("error", e.getMessage()+": "+(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);

        }

        response.put("mensaje", "El producto ha sido actualizado con éxito!");
        response.put("Producto", productoUpdated);

        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
    }

    @PutMapping("/producto/delete/{id}")
    public ResponseEntity<?> delete(@RequestBody Productos producto, @PathVariable Long id) {

        Productos productoActual = productoService.findById(id);
        Productos productoUpdated;

        Map<String, Object> response = new HashMap<>();

        if (productoActual == null) {
            response.put("mensaje", "Eror: no se puede eliminar, el producto con el ID: ".concat(id.toString().concat(". No existe")));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }
        try {

            productoActual.setEstado(producto.getEstado());

            productoUpdated = productoService.save(productoActual);

        } catch (DataAccessException e) {

            response.put("mensaje", "Error al eliminar el producto");
            response.put("error", e.getMessage()+": "+(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);

        }

        response.put("mensaje", "El Producto ha sido eliminado con éxito!");
        response.put("Producto", productoUpdated);

        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
    }

    @PostMapping("/producto/upload")
	public ResponseEntity<?> upload(@RequestParam("archivo") MultipartFile archivo, @RequestParam("id") Long id) {

		Map<String, Object> response = new HashMap<>();

		Productos producto = productoService.findById(id);

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

			String nombreLogoAnterior = producto.getImgProducto();

			if (nombreLogoAnterior != null && nombreLogoAnterior.length() > 0) {
				Path rutaLogoAnterior = Paths.get("uploads").resolve(nombreLogoAnterior).toAbsolutePath();
				File archivoLogoAnterior = rutaLogoAnterior.toFile();
				if (archivoLogoAnterior.exists() && archivoLogoAnterior.canRead()) {
					archivoLogoAnterior.delete();
				}
			}

			producto.setImgProducto(nombreArchivo);

			productoService.save(producto);

			response.put("producto", producto);
			response.put("mensaje", "Has subido correctamente la imagen: " + nombreArchivo);
		}

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
    }
    
		@GetMapping("/uploads/img/{nombreLogo:.+}")
		public ResponseEntity<Resource> verLogo(@PathVariable String nombreLogo) {

			Path rutaArchivo = Paths.get("uploads").resolve(nombreLogo).toAbsolutePath();
			Resource recurso = null;
			try {
				recurso = new UrlResource(rutaArchivo.toUri());
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}

			assert recurso != null;
			if (!recurso.exists() && !recurso.isReadable()) {
				throw new RuntimeException("Error no se pudo cargar la imagen: " + nombreLogo);
			}

			HttpHeaders cabecera = new HttpHeaders();
			cabecera.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + recurso.getFilename() + "\"");
			return new ResponseEntity<Resource>(recurso, cabecera, HttpStatus.OK);
		}
}

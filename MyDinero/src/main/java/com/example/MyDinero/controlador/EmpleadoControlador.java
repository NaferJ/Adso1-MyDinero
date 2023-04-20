package com.example.MyDinero.controlador;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.MyDinero.modelo.Empleado;
import com.example.MyDinero.repositorio.EmpleadoRepositorio;

@RestController
@RequestMapping("/api/v1/")
public class EmpleadoControlador {
	
	@Autowired
	private EmpleadoRepositorio repositorio;
	
	//Este metodo sirve para listar todos los empleados
	@GetMapping("/empleados")
	public List<Empleado> listarTodosLosEmpleados(){
		return repositorio.findAll();
	}
	
	//Este metodo sirve para guardar al empleado
	@PostMapping("/empleados")
	public Empleado guardarEmpleado(@RequestBody Empleado empleado) {
		return repositorio.save(empleado);
	}
}

package com.example.MyDinero.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.MyDinero.modelo.Empleado;


public interface EmpleadoRepositorio extends JpaRepository<Empleado, Long>{

	
}

package com.example.MyDinero.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.MyDinero.modelo.Empleado;


@Repository
public interface EmpleadoRepositorio extends JpaRepository<Empleado, Long>{

	
}

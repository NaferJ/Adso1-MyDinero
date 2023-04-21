package com.example.MyDinero.excepciones;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundExcepcion extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1297110099694367790L;
	
	public ResourceNotFoundExcepcion(String mensaje) {
		super(mensaje);
	}
}
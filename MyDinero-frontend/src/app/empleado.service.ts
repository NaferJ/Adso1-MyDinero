import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Empleado } from './empleado';

@Injectable({
  providedIn: 'root'
})
export class EmpleadoService {

  private baseURL = "http://localhost:8080/api/v1/empleados";

  constructor(private httpClient : HttpClient) { }

  //Este metodo nos sirve para obtener los empleados
  obtenerListaEmpleados():Observable<Empleado[]>{
    return this.httpClient.get<Empleado[]>(`${this.baseURL}`);
  }

  //Este metodo nos sirve para registrar un empleado
  registrarEmpleado(empleado:Empleado): Observable<Object>{
    return this.httpClient.post(`${this.baseURL}`,empleado);
  }
}

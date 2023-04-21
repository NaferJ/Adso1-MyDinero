import { Component } from '@angular/core';
import { Empleado } from '../empleado';

@Component({
  selector: 'app-lista-empleados',
  templateUrl: './lista-empleados.component.html',
  styleUrls: ['./lista-empleados.component.css']
})
export class ListaEmpleadosComponent {
  empleados: Empleado[];

  constructor(){}

  ngOnInit() {
    this.empleados = [{
      "id":1,
      "nombre":"Christian",
      "apellido" : "Ramirez",
      "email" : "cristian@gmail.com"
    },
    {
      "id":2,
      "nombre":"Gabriel",
      "apellido" : "Ramirez",
      "email" : "gabriel@gmail.com"
    }];
  }
}

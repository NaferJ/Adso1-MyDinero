import { Component } from '@angular/core';
import { Empleado } from '../empleado';
import { EmpleadoService } from '../empleado.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-registrar-empleado',
  templateUrl: './registrar-empleado.component.html',
  styleUrls: ['./registrar-empleado.component.css']
})
export class RegistrarEmpleadoComponent {
  
  empleado: Empleado = new Empleado();
  constructor(private empleadoServicio:EmpleadoService,private router:Router){}

  ngOnInit(): void {
    
  }

  guardarEmpleado(){
    this.empleadoServicio.registrarEmpleado(this.empleado).subscribe(dato => {
      console.log(dato);
    },error => console.log(error));
  }

  onSubmit(){
    console.log(this.empleado);
  }
}

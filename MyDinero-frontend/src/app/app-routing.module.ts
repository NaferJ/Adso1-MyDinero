import { EmpleadoDetallesComponent } from './empleado-detalles/empleado-detalles.component';
import { ActualizarEmpleadoComponent } from './actualizar-empleado/actualizar-empleado.component';
import { ListaEmpleadosComponent } from './lista-empleados/lista-empleados.component';
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { RegistrarEmpleadoComponent } from './registrar-empleado/registrar-empleado.component';

const routes: Routes = [
  {path : 'empleados',component:ListaEmpleadosComponent},
  {path:'',redirectTo:'empleados',pathMatch:'full'},
  {path : 'registrar-empleado',component : RegistrarEmpleadoComponent},
  {path : 'actualizar-empleado/:id',component : ActualizarEmpleadoComponent},
  {path : 'empleado-detalles/:id',component : EmpleadoDetallesComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
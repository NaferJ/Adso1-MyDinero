import { Routes } from '@angular/router';

import { DashboardComponent } from '../../pages/dashboard/dashboard.component';
import { UserComponent } from '../../pages/user/user.component';
import { EntidadComponent } from '../../pages/entidades/entidad.component';
import { ProductosComponent } from '../../pages/productos/productos.component';
import { PuestosComponent } from '../../pages/puestos/puestos.component';
import { BienesComponent } from '../../pages/bienes/bienes.component';
import { UnidadComponent } from '../../pages/unidad/unidad.component';
import { ProveedoresComponent } from '../../pages/proveedores/proveedores.component';
import { GruposComponent } from '../../pages/grupos/grupos.component';
import { ParametrosComponent } from '../../pages/parametros/parametros.component';
import { UsuariosComponent } from '../../pages/usuarios/usuarios.component';
import { UbicacionComponent } from '../../pages/ubicacion/ubicacion.component';
import { AcercadeComponent } from '../../pages/acercade/acercade.component';
import { AgendaComponent } from '../../pages/agenda/agenda.component';
import { UserprofileprofileComponent } from 'app/pages/userprofile/userprofileprofileprofileprofile.component';

export const AdminLayoutRoutes: Routes = [
  { path: 'dashboard', component: DashboardComponent },
  { path: 'user', component: UserComponent },
  { path: 'entidades', component: EntidadComponent },
  { path: 'productos', component: ProductosComponent},
  { path: 'puestos', component: PuestosComponent},
  { path: 'bienes', component: BienesComponent},
  { path: 'unidad', component: UnidadComponent},
  { path: 'proveedores', component: ProveedoresComponent},
  { path: 'grupos', component: GruposComponent},
  { path: 'parametros', component: ParametrosComponent},
  { path: 'usuarios', component: UsuariosComponent},
  { path: 'ubicacion', component: UbicacionComponent},
  { path: 'acercade', component: AcercadeComponent},
  { path: 'agenda', component: AgendaComponent},
  {path: 'user-profile', component: UserprofileprofileComponent}
];

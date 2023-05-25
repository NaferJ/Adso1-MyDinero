import { NgModule, LOCALE_ID } from '@angular/core';
import { RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { SweetAlert2Module } from '@sweetalert2/ngx-sweetalert2';
import { NgxSpinnerModule } from 'ngx-spinner';
import { NgxSmartModalModule, NgxSmartModalService } from 'ngx-smart-modal';
// tslint:disable-next-line: max-line-length
import { MatInputModule, MatFormFieldModule, MatIconModule, MatButtonModule, MatSlideToggleModule, MatDividerModule, MatDialogModule, MatSelectModule, MatTooltipModule, MatDatepickerModule} from '@angular/material';
import {MatMomentDateModule} from '@angular/material-moment-adapter';
import { ToastrModule } from 'ngx-toastr';
import { CurrencyMaskModule } from 'ngx-currency-mask';
import { NgxPaginationModule } from 'ngx-pagination';
import { AdminLayoutRoutes } from './admin-layout.routing';

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
import { UbicacionComponent } from '../../pages/ubicacion/ubicacion.component';
import { AcercadeComponent } from '../../pages/acercade/acercade.component';
import { EntidadFormComponent } from '../../pages/entidades/entidad-form/entidad-form.component';
import { ProductosFormComponent } from '../../pages/productos/productos-form/productos-form.component';
import { ProveedoresFormComponent } from '../../pages/proveedores/proveedores-form/proveedores-form.component';
import { UnidadFormComponent } from '../../pages/unidad/unidad-form/unidad-form.component';
import { UsuariosComponent } from '../../pages/usuarios/usuarios.component';
import { PuestosFormComponent } from '../../pages/puestos/puestos-form/puestos-form.component';
import { BienesFormComponent } from '../../pages/bienes/bienes-form/bienes-form.component';
import { GruposFormComponent } from 'app/pages/grupos/grupos-form/grupos-form.component';

import { MapServService } from '../../services/map-serv.service';

import { registerLocaleData } from '@angular/common';
import { ParametrosFormComponent } from '../../pages/parametros/parametros-form/parametros-form.component';
import { AgendaComponent } from '../../pages/agenda/agenda.component';

import { FlatpickrModule } from 'angularx-flatpickr';
import { CalendarModule, DateAdapter } from 'angular-calendar';
import { adapterFactory } from 'angular-calendar/date-adapters/date-fns';

import localeEs from '@angular/common/locales/es-CO';
import { UserprofileprofileComponent } from 'app/pages/userprofile/userprofileprofileprofileprofile.component';
registerLocaleData(localeEs, 'es-Co');

@NgModule({
  imports: [
    CommonModule,
    RouterModule.forChild(AdminLayoutRoutes),
    FormsModule,
    NgbModule,
    ReactiveFormsModule,
    CurrencyMaskModule,
    NgxSpinnerModule,
    MatFormFieldModule, MatIconModule, MatInputModule, MatButtonModule,
    MatSlideToggleModule, MatDividerModule, MatDialogModule, MatSelectModule,
    MatTooltipModule, MatDatepickerModule,
    NgxPaginationModule, MatMomentDateModule,
    FlatpickrModule.forRoot(),
    CalendarModule.forRoot({
      provide: DateAdapter,
      useFactory: adapterFactory
    }),
    ToastrModule.forRoot(),
    SweetAlert2Module.forRoot(),
    NgxSmartModalModule.forRoot()
  ],
  declarations: [
    AcercadeComponent,
    AgendaComponent,
    BienesComponent,
    BienesFormComponent,
    DashboardComponent,
    EntidadComponent,
    EntidadFormComponent,
    GruposComponent,
    GruposFormComponent,
    ParametrosComponent,
    ProductosComponent,
    ProductosFormComponent,
    ProveedoresComponent,
    ProveedoresFormComponent,
    PuestosComponent,
    PuestosFormComponent,
    UbicacionComponent,
    UnidadComponent,
    UnidadFormComponent,
    UserComponent,
    UsuariosComponent,
    ParametrosFormComponent,
    UserprofileprofileComponent
  ],
  providers: [
    NgxSmartModalService,
    MapServService,
    { provide: LOCALE_ID, useValue: 'es-Co' }
  ],
  entryComponents: [
    EntidadFormComponent,
    ProductosFormComponent,
    ProveedoresFormComponent,
    PuestosFormComponent,
    UnidadFormComponent,
    BienesFormComponent,
    GruposFormComponent,
    ParametrosFormComponent
  ]
})

export class AdminLayoutModule {}

import { Component, OnInit } from '@angular/core';
import { AuthService } from '../services/auth.service';


export interface RouteInfo {
  path: string;
  title: string;
  icon: string;
  class: string;
}

export const ROUTES_OPERATIVO: RouteInfo[] = [
  { path: '/user-profile', title: 'Su Perfil', icon: 'nc-single-02', class: '' }
];

export const ROUTES_ADMIN: RouteInfo[] = [
  { path: '/dashboard', title: 'panel de control', icon: 'nc-layout-11', class: '' },
  { path: '/user', title: 'Entidad Perfil', icon: 'nc-single-02', class: '' },
  { path: '/proveedores', title: 'Proveedores', icon: 'nc-box-2', class: ''},
  { path: '/unidad', title: 'Unidades Medida', icon: 'nc-chart-bar-32', class: ''},
  { path: '/productos', title: 'Productos', icon: 'nc-cart-simple', class: ''},
  { path: '/puestos', title: 'Puestos Trabajo', icon: 'nc-vector', class: ''},
  { path: '/bienes', title: 'Bienes Servicios', icon: 'nc-money-coins', class: ''},
  { path: '/usuarios', title: 'Usuarios', icon: 'nc-badge', class: ''},
  { path: '/acercade', title: 'Nosotros', icon: 'nc-alert-circle-i', class: ''}
];

export const ROUTES_CLIENTE: RouteInfo[] = [
  { path: '/user-profile', title: 'Su Perfil', icon: 'nc-single-02', class: '' },
  { path: '/agenda', title: 'Citas', icon: 'nc-calendar-60', class: ''},
  { path: '/acercade', title: 'Nosotros', icon: 'nc-alert-circle-i', class: ''}
];

export const ROUTES_SUPER: RouteInfo[] = [
  { path: '/dashboard', title: 'panel de control', icon: 'nc-layout-11', class: '' },
  { path: '/user', title: 'Entidad Perfil', icon: 'nc-single-02', class: '' },
  { path: '/entidades', title: 'Entidades', icon: 'nc-bank', class: '' },
  { path: '/usuarios', title: 'Usuarios', icon: 'nc-badge', class: ''},
  { path: '/grupos', title: 'Grupos', icon: 'nc-bullet-list-67', class: ''},
  { path: '/parametros', title: 'Parametros', icon: 'nc-settings', class: ''},
  { path: '/ubicacion', title: 'Ubicación', icon: 'nc-icon nc-pin-3', class: ''},
  { path: '/acercade', title: 'Nosotros', icon: 'nc-alert-circle-i', class: ''}
]

@Component({
  moduleId: module.id,
  // tslint:disable-next-line: component-selector
  selector: 'sidebar-cmp',
  templateUrl: 'sidebar.component.html',
  styleUrls: ['sidebar.component.scss']
})

export class SidebarComponent implements OnInit {
  usuario: any;
  roles: any;
  public menuItems: any[];
  constructor(private auth: AuthService) {
    this.usuario = JSON.parse(sessionStorage.getItem('usuario'));
    this.roles = this.usuario.roles[0].nombre;
  }
  ngOnInit() {
    switch (this.roles) {
      case 'ROLE_ADMIN':
        this.menuItems = ROUTES_ADMIN.filter(menuItem => menuItem);
        break;
      case 'ROLE_OPERATIVO':
        this.menuItems = ROUTES_OPERATIVO.filter(menuItem => menuItem);
        break;
      case 'ROLE_CLIENTE':
        this.menuItems = ROUTES_CLIENTE.filter(menuItem => menuItem);
        break;
      case 'ROLE_SUPER':
        this.menuItems = ROUTES_SUPER.filter(menuItem => menuItem);
        break;
      default:
        break;
    }
  }
}

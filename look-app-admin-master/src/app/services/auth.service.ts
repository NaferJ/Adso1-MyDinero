import { Injectable, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Usuario } from '../class/usuario';
import { ENDPOINTS } from '../appSettings';
import { NgxSpinnerService } from 'ngx-spinner';

@Injectable({
  providedIn: 'root'
})
export class AuthService implements OnInit {

  public _usuario: Usuario;
  public _token: string;

  constructor(private http: HttpClient, private spinner: NgxSpinnerService) { }
  ngOnInit() { }

  public get usuario(): Usuario {
    if ( this._usuario !== null ) {
      return this._usuario;
    } else if ( this._usuario === null && sessionStorage.getItem('usuario') !== null) {
      this._usuario = JSON.parse(sessionStorage.getItem('usuario')) as Usuario;
      return this._usuario;
    }
    return new Usuario();
  }

  public get token(): string {
    if ( this._token != null ) {
      return this._token;
    } else if ( this._token == null && sessionStorage.getItem('token') != null) {
      this._token = sessionStorage.getItem('token');
      return this._token;
    }
  }

  login(usuario: Usuario): Observable<any> {

    const urlEndPoint = ENDPOINTS.dev.auth;

    const credenciales = btoa('angularapp' + ':' + '12345');

    const httpHeaders = new HttpHeaders({
      'Content-Type' : 'application/x-www-form-urlencoded',
      'Authorization' : 'Basic ' + credenciales,
    });

    const params = new URLSearchParams();
    params.set ('grant_type', 'password');
    params.set ('username', usuario.username);
    params.set ('password', usuario.password);

    return this.http.post<any>(
      urlEndPoint, params.toString(),
      {
        headers: httpHeaders
      }
    );
  }

  guardarUsuario( access_token: string): void {
    const payload = this.obtenerDatosToken(access_token);
    this._usuario = new Usuario();
    this._usuario.id = payload.id;
    this._usuario.nombres = payload.nombres;
    this._usuario.apellidos = payload.apellidos;
    this._usuario.email = payload.email;
    this._usuario.entidad = payload.entidad;
    this._usuario.enabled = payload.enabled;
    this._usuario.username = payload.username;
    this._usuario.roles  = payload.roles;
    sessionStorage.setItem('usuario', JSON.stringify(this._usuario));
  }

  guardarToken( access_token: string): void {
    this._token = access_token;
    sessionStorage.setItem('token', access_token);
  }

  obtenerDatosToken( access_token: string): any {
    if (access_token != null) {
      return JSON.parse(atob(access_token.split('.')[1]));
    }
    return null;
  }

  isAuthenticated(): boolean {
    const payload = this.obtenerDatosToken(this._token);

    if (payload != null && payload.user_name && payload.username.length > 0) {
      return true;
    }
    return false;
  }

  hasRole(role: any): boolean {
    if (this.usuario != null && this.usuario.roles != null && this.usuario.roles.includes(role)) {
      return true;
    }
    return false;
  }

  logout(): void {
    this._token = null;
    this._usuario = null;
    sessionStorage.clear();
    sessionStorage.removeItem('token');
    sessionStorage.removeItem('usuario');
  }

  obtenerDatosUser() {
    if ( this._usuario !== null ) {
      return this._usuario;
    } else if ( this._usuario === null && sessionStorage.getItem('usuario') !== null) {
      this._usuario = JSON.parse(sessionStorage.getItem('usuario')) as Usuario;
      return this._usuario;
    }
    return new Usuario();
  }
}

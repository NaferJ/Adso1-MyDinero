import { Injectable, OnInit } from '@angular/core';
import { HttpClient, HttpHeaders, HttpRequest, HttpEvent, HttpResponse,
  HttpHandler, HttpInterceptor, HttpErrorResponse} from '@angular/common/http';
import { Router } from '@angular/router';
import { AuthService } from './auth.service';
import { Observable, throwError  } from 'rxjs';
import { map, catchError } from 'rxjs/operators';
import { ENDPOINTS } from '../appSettings';
import Swal from 'sweetalert2';

@Injectable({
  providedIn: 'root'
})
export class AppService implements OnInit {

  usuario: any;
  url: string;
  private httpHeaders = new HttpHeaders({'Content-type': 'application/json'});
  private httpHeaders2 = new HttpHeaders('multipart/*');

  constructor(private http: HttpClient, private auth: AuthService, private router: Router) {
    this.url = ENDPOINTS.dev.api;
  }

  ngOnInit() {
    this.usuario = JSON.parse(sessionStorage.getItem('usuario'));
  }

  public isNotAuthorized(e): boolean {
    if (e.status === 401) {
      this.router.navigate(['/auth']);
      return true;
    }

    if (e.status === 403) {
      Swal.fire('Acceso Denegado', `Hola ${this.usuario.username}`, 'info');
      this.router.navigate(['/login']);
      return true;
    }

    return false;
  }

  public agregarAuthorizationHeader2() {
    const token = this.auth.token;
    if (token != null) {
      return this.httpHeaders2.append('Authorization', 'Bearer' + token);
    }
    return this.httpHeaders2;
  }

  public agregarAuthorizationHeader() {
    const token = this.auth.token;
    if (token != null) {
      return this.httpHeaders.append('Authorization', 'Bearer' + token);
    }
    return this.httpHeaders;
  }


  get(ruta: string) {
    return this.http.get<any>(this.url.concat(ruta), {headers: this.agregarAuthorizationHeader()});
  }

  post(ruta: string, body: any) {
    return this.http.post<any>(this.url.concat(ruta), body, {headers: this.agregarAuthorizationHeader()});
  }

  delete(ruta: string) {
    return this.http.delete<any>(this.url.concat(ruta), {headers: this.agregarAuthorizationHeader()});
  }

  put(ruta: string, body: any) {
    return this.http.put<any>(this.url.concat(ruta), body, {headers: this.agregarAuthorizationHeader()});
  }

  subirImg(archivo: File, id): Observable<HttpEvent<{}>> {
    const formData = new FormData();
    formData.append('archivo', archivo);
    formData.append('id', id);

    const req = new HttpRequest('POST', `${this.url}/producto/upload`, formData, {
      headers: this.agregarAuthorizationHeader2(),
      reportProgress: true
    });

    return this.http.request(req);
  }

  subirLogo(archivo: File, id): Observable<HttpEvent<{}>> {
    const formData = new FormData();
    formData.append('archivo', archivo);
    formData.append('id', id);

    const req = new HttpRequest('POST', `${this.url}/entidad/upload`, formData, {
      headers: this.agregarAuthorizationHeader2(),
      reportProgress: true
    });

    return this.http.request(req);
  }

  subirImagen(archivo: File, id): Observable<HttpEvent<{}>> {
    const formData = new FormData();
    formData.append('archivo', archivo);
    formData.append('id', id);

    const req = new HttpRequest('POST', `${this.url}/usuario/upload`, formData, {
      headers: this.agregarAuthorizationHeader2(),
      reportProgress: true
    });

    return this.http.request(req);
  }
}

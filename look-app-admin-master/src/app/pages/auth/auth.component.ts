import { Component, OnInit, AfterContentChecked } from '@angular/core';
import Swal from 'sweetalert2';
import { AuthService } from '../../services/auth.service';
import { Router } from '@angular/router';
import { Usuario } from '../../class/usuario';
import { NgxSpinnerService } from 'ngx-spinner';

const Toast = Swal.mixin({
  toast: true,
  position: 'top',
  showConfirmButton: false,
  timer: 3000
});

@Component({
  selector: 'app-auth',
  templateUrl: './auth.component.html',
  styleUrls: ['./auth.component.scss']
})
export class AuthComponent implements OnInit, AfterContentChecked {

  title = 'Iniciar Sesión';
  public showSpinner: boolean;
  usuario: Usuario;

  constructor(private auth: AuthService, private router: Router, private spinner: NgxSpinnerService) {
    this.usuario = new Usuario();
  }

  ngOnInit() { }
  ngAfterContentChecked() {
    this.spinner.show();
  }
  login(): void {
    if (this.usuario.username === undefined || this.usuario.password === undefined) {
      Toast.fire({
        type: 'warning',
        title: 'Por favor diligencie todos los campos'
      });
    } else {
      this.auth.login(this.usuario).subscribe(
        response => {
          this.auth.guardarUsuario(response.access_token);
          this.auth.guardarToken(response.access_token);
          const usuario = this.auth.usuario;
          this.router.navigate(['/dashboard']);
          Swal.fire({
            position: 'center',
            title: 'Login',
            text: `Hola ${usuario.username}, Bienvenido a LOOK-APP`,
            type: 'success',
            showConfirmButton: false,
            customClass: {
              popup: 'animated tada'
            },
            timer: 2000
          });
        },
        err => {
          if (err.status === 400) {
            Toast.fire({
              type: 'error',
              title: 'Usuario o contraseña incorrecta'
            });
          }
        }
      );
    }
  }
}

import { Component, OnInit } from '@angular/core';
import { AppService } from '../../services/app.service';
import { AuthService } from '../../services/auth.service';
import Swal from 'sweetalert2';

const Toast = Swal.mixin({
  toast: true,
  position: 'bottom-start',
  showConfirmButton: false,
  timer: 3000
});

@Component({
  selector: 'app-usuarios',
  templateUrl: './usuarios.component.html',
  styleUrls: ['./usuarios.component.scss']
})
export class UsuariosComponent implements OnInit {
  public idEntidad: any;
  public usuario: any;
  public usuarios: any;
  public entidad: any;

  constructor(private appService: AppService, private auth: AuthService) {
    const { entidad: { id } } = JSON.parse(sessionStorage.getItem('usuario'));
    this.idEntidad = {
      id: id
    }
  }

  ngOnInit() {
    this.listarUsuarios();
  }

  public refreshList() {
    this.listarUsuarios();
  }


  listarUsuarios() {
    this.appService.get(`/usuarios/entidad/${this.idEntidad.id}`).subscribe(
      result => {
        this.usuarios = result;
        console.log('Usuarios->', this.usuarios);
      }, error => {
        console.log(error.message);
      }
    )
  }

}

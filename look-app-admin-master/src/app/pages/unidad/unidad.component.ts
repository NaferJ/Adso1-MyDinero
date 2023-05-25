import { Component, OnInit } from '@angular/core';
import { AuthService } from '../../services/auth.service';
import { MatDialog } from '@angular/material/dialog';
import { AppService } from '../../services/app.service';
import Swal from 'sweetalert2';
import { UnidadFormComponent } from './unidad-form/unidad-form.component';

const Toast = Swal.mixin({
  toast: true,
  position: 'bottom-start',
  showConfirmButton: false,
  timer: 3000
});

@Component({
  selector: 'app-unidad',
  templateUrl: './unidad.component.html',
  styleUrls: ['./unidad.component.scss']
})
export class UnidadComponent implements OnInit {

  public idEntidad: any;
  public unidades: any;
  public itemSelected: any;
  public typeForm: number;
  public pageNow = 1;

  constructor(private auth: AuthService, private appService: AppService,  public dialog: MatDialog) { }

  ngOnInit() {
    const { entidad: { id } } = JSON.parse(sessionStorage.getItem('usuario'));
    this.idEntidad = id;
    this.listarUnidades();
  }

  listarUnidades() {
    this.appService.get(`/unidades/list`).subscribe(
      result => {
        this.unidades = result;
        console.log(this.unidades);
      },
      error => {
        console.log(error.message);
      }
    )
  }

  public openDialog ( ) {
    const dialogRef = this.dialog.open(UnidadFormComponent, {
      data: {
        typeForm: this.typeForm,
        data: this.itemSelected
      },
      width: 'auto',
      height: '95%',
      disableClose: true
    });

    dialogRef.afterClosed().subscribe(result => {
      if (result.tipo === 1) {
        Toast.fire({
          type: 'success',
          title: result.message
        });
        this.listarUnidades();
      }
    });
  }

  public createUnidad() {
    this.itemSelected = null;
    this.typeForm = 0;
    this.openDialog();
  }

  public updateUnidad(item: any) {
    this.itemSelected = item;
    this.typeForm = 1;
    this.openDialog();
  }



  public deleteUnidad(item: any) {
    Swal.fire({
      title: 'Estás seguro?',
      text: 'Está acción no se puede revertir!',
      type: 'warning',
      showCancelButton: true,
      confirmButtonColor: '#3085d6',
      cancelButtonColor: '#d33',
      confirmButtonText: 'Si eliminar!'
    }).then((result) => {
      if (result.value) {
        this.appService.put(`/unidad/delete/${item.id}`, item).subscribe(
          response => {
            Toast.fire({
              type: 'success',
              title: response.mensaje
            });
            this.listarUnidades();
          },
          error => {
            console.error(error.error);
          }
        )
      }
    })
  }

  public setEstado(item: any) {
    switch (item.estado) {
      case true:
        item.estado = false;
        this.deleteUnidad(item);
        break;
      case false:
        item.estado = true;
        this.deleteUnidad(item);
        break
      default:
        break;
    }
  }

}

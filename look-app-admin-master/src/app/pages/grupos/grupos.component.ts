import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import Swal from 'sweetalert2';
import { AppService } from '../../services/app.service';
import { GruposFormComponent } from './grupos-form/grupos-form.component';

const Toast = Swal.mixin({
  toast: true,
  position: 'bottom-start',
  showConfirmButton: false,
  timer: 3000
});


@Component({
  selector: 'app-grupos',
  templateUrl: './grupos.component.html',
  styleUrls: ['./grupos.component.scss']
})
export class GruposComponent implements OnInit {

  public grupos: any;
  public idEntidad: any;
  public itemSelected: any;
  public typeForm: number;
  public pageNow = 1;

  constructor(private appService: AppService, public dialog: MatDialog) { }

  ngOnInit() {
    const { entidad: { id } } = JSON.parse(sessionStorage.getItem('usuario'));
    this.idEntidad = id;
    this.listarGrupos();
  }

  refreshList() {
    this.listarGrupos();
  }

  listarGrupos() {
    this.appService.get(`/grupos/list`).subscribe(
      result => {
        this.grupos = result;
        console.log(`grupos->`, this.grupos);
      },
      error => {
        console.log(error.message);
      }
    )
  }

  public openDialog ( ) {
    const dialogRef = this.dialog.open(GruposFormComponent, {
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
        this.listarGrupos();
      }
    });
  }

  public createGrupo() {
    this.itemSelected = null;
    this.typeForm = 0;
    this.openDialog();
  }

  public updateGrupo(item: any) {
    this.itemSelected = item;
    this.typeForm = 1;
    this.openDialog();
  }



 /*
 *
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
*
*/

}

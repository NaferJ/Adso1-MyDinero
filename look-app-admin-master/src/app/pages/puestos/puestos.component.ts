import { Component, OnInit } from '@angular/core';
import { AuthService } from '../../services/auth.service';
import { AppService } from '../../services/app.service';
import { MatDialog } from '@angular/material/dialog';
import Swal from 'sweetalert2';
import { PuestosFormComponent } from '../puestos/puestos-form/puestos-form.component';

const Toast = Swal.mixin({
  toast: true,
  position: 'bottom-start',
  showConfirmButton: false,
  timer: 3000
});

@Component({
  selector: 'app-puestos',
  templateUrl: './puestos.component.html',
  styleUrls: ['./puestos.component.scss']
})
export class PuestosComponent implements OnInit {

  public puestos: any;
  public idEntidad: any;
  public itemSelected: any;
  public typeForm: number;
  public pageNow = 1;

  constructor(private auth: AuthService, private appService: AppService, public dialog: MatDialog) { }

  ngOnInit() {
    const { entidad: { id } } = JSON.parse(sessionStorage.getItem('usuario'));
    this.idEntidad = id;
    this.listarPuestos();
  }


  public refreshList() {
    this.listarPuestos();
  }

  listarPuestos() {
    this.appService.get(`/puestos/list`).subscribe(
      result => {
        this.puestos = result;
        console.log(this.puestos);
      },
      error => {
        console.log(error);
      }
    )
  }

  public openDialog ( ) {
    const dialogRef = this.dialog.open(PuestosFormComponent, {
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
        this.listarPuestos();
      }
    });
  }

  public createPuesto() {
    this.itemSelected = null;
    this.typeForm = 0;
    this.openDialog();
  }

  public updatePuesto(item: any) {
    this.itemSelected = item;
    this.typeForm = 1;
    this.openDialog();
  }



  deletePuesto(item: any) {
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
        this.appService.put(`/puesto/delete/${item.id}`, item).subscribe(
          response => {
            Toast.fire({
              type: 'success',
              title: response.mensaje
            });
            this.listarPuestos();
          },
          error => {
            console.error(error.error);
          }
        )
      }
    })
  }

  activarPuesto(item: any) {
    Swal.fire({
      title: 'Estás seguro?',
      text: 'Está acción no se puede revertir!',
      type: 'warning',
      showCancelButton: true,
      confirmButtonColor: '#3085d6',
      cancelButtonColor: '#d33',
      confirmButtonText: 'Si, Activar!'
    }).then((result) => {
      if (result.value) {
        this.appService.put(`/puesto/active/${item.id}`, item).subscribe(
          response => {
            Toast.fire({
              type: 'success',
              title: response.mensaje
            });
            this.listarPuestos();
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
        this.deletePuesto(item);
        break;
      case false:
        item.estado = true;
        this.activarPuesto(item);
        break
      default:
        break;
    }
  }
}

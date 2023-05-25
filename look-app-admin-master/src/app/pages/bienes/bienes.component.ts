import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import Swal from 'sweetalert2';
import { AppService } from '../../services/app.service';
import { AuthService } from '../../services/auth.service';
import { BienesFormComponent } from './bienes-form/bienes-form.component';

const Toast = Swal.mixin({
  toast: true,
  position: 'bottom-start',
  showConfirmButton: false,
  timer: 3000
});

@Component({
  selector: 'app-bienes',
  templateUrl: './bienes.component.html',
  styleUrls: ['./bienes.component.scss']
})
export class BienesComponent implements OnInit {
  public bienes: any;
  public idEntidad: any;
  public itemSelected: any;
  public typeForm: number;
  public pageNow = 1;
  constructor(private auth: AuthService, private appService: AppService , public dialog: MatDialog) { }

  ngOnInit() {
    this.listarBienes();
  }

  refreshList() {
    this.listarBienes();
  }

  listarBienes() {
    this.appService.get(`/bienes/list`).subscribe(
      result => {
        this.bienes = result;
        Toast.fire({
          type: 'success',
          title: 'Consulta exitosa'
        });
        console.log(this.bienes);
      },
      error => {
        console.log(error);
      }
    )
  }

  public openDialog ( ) {
    const dialogRef = this.dialog.open(BienesFormComponent, {
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
        this.listarBienes();
      }
    });
  }

  public createBien() {
    this.itemSelected = null;
    this.typeForm = 0;
    this.openDialog();
  }

  public updateBien(item: any) {
    this.itemSelected = item;
    this.typeForm = 1;
    this.openDialog();
  }



  deleteBien(item: any) {
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
        this.appService.put(`/bienservicio/delete/${item.id}`, item).subscribe(
          response => {
            Toast.fire({
              type: 'success',
              title: response.mensaje
            });
            this.listarBienes();
          },
          error => {
            console.error(error.error);
          }
        )
      }
    })
  }

  activarBien(item: any) {
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
        this.appService.put(`/bienservicio/active/${item.id}`, item).subscribe(
          response => {
            Toast.fire({
              type: 'success',
              title: response.mensaje
            });
            this.listarBienes();
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
        this.deleteBien(item);
        break;
      case false:
        item.estado = true;
        this.activarBien(item);
        break
      default:
        break;
    }
  }

}

import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import Swal from 'sweetalert2';
import { AppService } from '../../services/app.service';
import { ProveedoresFormComponent } from './proveedores-form/proveedores-form.component';

const Toast = Swal.mixin({
  toast: true,
  position: 'bottom-start',
  showConfirmButton: false,
  timer: 3000
});


@Component({
  selector: 'app-proveedores',
  templateUrl: './proveedores.component.html',
  styleUrls: ['./proveedores.component.scss']
})
export class ProveedoresComponent implements OnInit {

  public usuario: any;
  public proveedores: any;
  private idEntidad: any;
  public productos: any;
  public itemSelected: any;
  public typeForm: number;
  public pageNow = 1;

  constructor(private appService: AppService, public dialog: MatDialog) { }

  ngOnInit() {
    this.usuario = JSON.parse(sessionStorage.getItem('usuario'));
    this.idEntidad = this.usuario.entidad.id;
    this.listarProveedores();
  }
  refreshList() {
    this.listarProveedores();
  }

  listarProveedores() {
    this.appService.get(`/proveedores/list`).subscribe(
      result => {
        this.proveedores = result;
      },
      error => {
        console.log(error.message);
      }
    )
  }

  public openDialog ( ) {
    const dialogRef = this.dialog.open(ProveedoresFormComponent, {
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
        this.listarProveedores();
      }
    });
  }

  public updateProveedor(item: any) {
    this.itemSelected = item;
    this.typeForm = 1;
    this.openDialog();
  }

  public createProveedor() {
    this.itemSelected = null;
    this.typeForm = 0;
    this.openDialog();
  }

  public deleteProveedor(item: any) {
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
        this.appService.put(`/proveedor/delete/${item.id}`, item).subscribe(
          response => {
            Toast.fire({
              type: 'success',
              title: response.mensaje
            });
            this.listarProveedores();
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
        this.deleteProveedor(item);
        break;
      case false:
        item.estado = true;
        this.deleteProveedor(item);
        break
      default:
        break;
    }
  }
}

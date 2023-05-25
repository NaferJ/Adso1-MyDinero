import { Component, OnInit } from '@angular/core';
import { AuthService } from '../../services/auth.service';
import { AppService } from '../../services/app.service';
import Swal from 'sweetalert2';
import { NgxSmartModalService } from 'ngx-smart-modal';
import { ToastrService } from 'ngx-toastr';
import {MatDialog} from '@angular/material/dialog';
import { EntidadFormComponent } from './entidad-form/entidad-form.component';

const Toast = Swal.mixin({
  toast: true,
  position: 'top-right',
  showConfirmButton: false,
  timer: 3000
});

@Component({
  // tslint:disable-next-line: component-selector
  selector: 'entidad-cmp',
  moduleId: module.id,
  templateUrl: 'entidad.component.html',
  styleUrls: ['./entidad.component.css']

})

export class EntidadComponent implements OnInit {
  private usuario: any;
  public entidades: any;
  public itemSelected: any;
  public typeForm: number;
  constructor(
    private auth: AuthService, private appService: AppService,
    public ngxSmartModalService: NgxSmartModalService,
    public dialog: MatDialog, private toastr: ToastrService) {
  }
  ngOnInit() {
    this.listarEntidades();
  }

  public refreshList() {
    this.listarEntidades();
  }

  public listarEntidades() {
    this.appService.get(`/entidad/list`).subscribe(
      result => {
        this.entidades = result;
        console.log('Entidades->', this.entidades);
      },
      error => {
        console.log(error);
      }
    )
  }

  public openDialog() {
    const dialogRef = this.dialog.open(EntidadFormComponent, {
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
        this.listarEntidades();
      }
    });
  }

  public updateEntidad(item: any) {
    this.itemSelected = item;
    this.typeForm = 1;
    this.openDialog();
  }

  public createEntidad() {
    this.itemSelected = null;
    this.typeForm = 0;
    this.openDialog();
  }

  public deleteEntidad(item: any) {
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
        this.appService.put(`/entidad/delete/${item.id}`, item).subscribe(
          response => {
            Toast.fire({
              type: 'success',
              title: response.mensaje
            });
            this.listarEntidades();
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
        this.deleteEntidad(item);
        break;
      case false:
        item.estado = true;
        this.deleteEntidad(item);
        break
      default:
        break;
    }
  }
}

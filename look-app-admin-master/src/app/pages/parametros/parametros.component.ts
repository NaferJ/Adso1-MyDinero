import { Component, OnInit } from '@angular/core';
import Swal from 'sweetalert2';
import { AppService } from '../../services/app.service';
import { MatDialog } from '@angular/material/dialog';
import { ParametrosFormComponent } from './parametros-form/parametros-form.component';
const Toast = Swal.mixin({
  toast: true,
  position: 'bottom-start',
  showConfirmButton: false,
  timer: 3000
});

@Component({
  selector: 'app-parametros',
  templateUrl: './parametros.component.html',
  styleUrls: ['./parametros.component.scss']
})
export class ParametrosComponent implements OnInit {

  public idEntidad: any;
  public parametros: any;
  public itemSelected: any;
  public typeForm: number;
  public pageNow = 1;
  constructor(private appService: AppService, public dialog: MatDialog) { }

  ngOnInit() {
    const { entidad: { id } } = JSON.parse(sessionStorage.getItem('usuario'));
    this.idEntidad = id;
    this.listarParametros();
  }

  refreshList() {
    this.listarParametros();
  }

  listarParametros() {
    this.appService.get(`/parametros/list`).subscribe(
      result => {
        this.parametros = result;
        console.log(this.parametros);
      },
      error => {
        console.log(error.message);
      }
    )
  }

  public openDialog ( ) {
    const dialogRef = this.dialog.open(ParametrosFormComponent, {
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
        this.listarParametros();
      }
    });
  }

  public createParametro() {
    this.itemSelected = null;
    this.typeForm = 0;
    this.openDialog();
  }

  public updateParametro(item: any) {
    this.itemSelected = item;
    this.typeForm = 1;
    this.openDialog();
  }


}

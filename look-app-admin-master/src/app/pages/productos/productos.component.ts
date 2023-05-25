import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import Swal from 'sweetalert2';
import { AppService } from '../../services/app.service';
import { ProductosFormComponent } from '../productos/productos-form/productos-form.component';

const Toast = Swal.mixin({
  toast: true,
  position: 'top-right',
  showConfirmButton: false,
  timer: 3000
});

@Component({
  selector: 'app-productos',
  templateUrl: './productos.component.html',
  styleUrls: ['./productos.component.scss']
})

export class ProductosComponent implements OnInit {
  private usuario: any;
  private idEntidad: any;
  public productos: any;
  public productosEntidad: any;
  public itemSelected: any;
  public typeForm: number;
  public pageNow = 1;

  constructor(private appService: AppService, public dialog: MatDialog) { }

  ngOnInit() {
    this.usuario = JSON.parse(sessionStorage.getItem('usuario'));
    this.idEntidad = this.usuario.entidad.id;
    this.listarProductos();
    this.listarProductosByEntidad();
  }

  public refreshList() {
    this.listarProductos();
  }

  listarProductos() {
    this.appService.get(`/productos/list`).subscribe(
      result => {
        this.productos = result;
        console.log('Productos->', this.productos);
      },
      error => {
        console.log(error);
      }
    )
  }

  listarProductosByEntidad() {
    this.appService.get(`/productos/entidad/${this.idEntidad}`).subscribe(
      result => {
        this.productosEntidad = result;
        console.log('ProductosByEntidad->', result);
      }
    )
  }

  public openDialog() {
    const dialogRef = this.dialog.open(ProductosFormComponent, {
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
        this.listarProductos();
      }
    });
  }

  public updateProduct(item: any) {
    this.itemSelected = item;
    this.typeForm = 1;
    this.openDialog();
  }

  public createProduct() {
    this.itemSelected = null;
    this.typeForm = 0;
    this.openDialog();
  }

  public deleteProduct(item: any) {
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
        this.appService.put(`/producto/delete/${item.id}`, item).subscribe(
          response => {
            Toast.fire({
              type: 'success',
              title: response.mensaje
            });
            this.listarProductos();
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
        this.deleteProduct(item);
        break;
      case false:
        item.estado = true;
        this.deleteProduct(item);
        break
      default:
        break;
    }
  }
}

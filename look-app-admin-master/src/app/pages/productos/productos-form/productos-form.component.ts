import { Component, OnInit, Inject } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { AppService } from '../../../services/app.service';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material';
import { AuthService } from '../../../services/auth.service';
import { ENDPOINTS } from '../../../appSettings';

@Component({
  // tslint:disable-next-line: component-selector
  selector: 'productos-form',
  templateUrl: './productos-form.component.html',
  styleUrls: ['./productos-form.component.scss']
})

export class ProductosFormComponent implements OnInit {
  private idEntidad: any;
  public datos: FormGroup;
  public proveedores: any;
  public unidadMedida: any;
  public imageSelected = null;
  public imgUrl: any;

  constructor(private formBuilder: FormBuilder, private appService: AppService,
    public dialogRef: MatDialogRef<ProductosFormComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any, public auth: AuthService) {

    this.datos = this.formBuilder.group(
      {
        nombre: ['', Validators.required],
        cantidad: ['', Validators.required],
        codigoBar: ['', Validators.required],
        noFactura: ['', Validators.required],
        precioCompra: ['', Validators.required],
        estado: [1, Validators.required],
        precioVenta: ['', Validators.required],
        stockMin: ['', Validators.required],
        ubicacion: ['', Validators.required],
        utilidad: ['', Validators.required],
        utilidadEsperada: ['', Validators.required],
        imgProducto: ['', Validators.required],
        proveedores: ['', Validators.required],
        unidadMedida: ['', Validators.required],
        descripcion: ['', Validators.required]
      }
    );
  }

  ngOnInit() {
    this.listUnidadesMedida();
    this.listarProveedores();

    const { entidad: { id } } = JSON.parse(sessionStorage.getItem('usuario'));
    this.idEntidad = id;

    if (this.data.typeForm === 1 && this.data.data !== null) {
      this.datos.patchValue({
        ...this.data.data,
        unidadMedida: this.data.data.unidadMedida[0].id,
        proveedores: this.data.data.proveedores[0].id
      })
      this.imgUrl = ENDPOINTS.dev.uploads + this.data.data.imgProducto;
    }
  }

  setProducto() {
    return {
      ...this.datos.value,
      proveedores: [{
        id: this.datos.value.proveedores
      }],
      unidadMedida: [{
        id: this.datos.value.unidadMedida
      }],
      idEntidad: {
        id: this.idEntidad
      }
    };
  }

  updateProducto() {
    this.appService.put(`/producto/update/${this.data.data.id}`, this.setProducto()).subscribe(
      ({mensaje, Producto}) => {
        if (Producto && Producto.id) {
          this.appService.subirImg(this.imageSelected, Producto.id).subscribe(result => {
            this.matDialogClose(1, mensaje);
          });
        }
      }, error => {
        console.log(error.message);
      }
    )
  }

  createProducto() {
    this.appService.post('/producto/new', this.setProducto()).subscribe(
      ({mensaje, Producto}) => {
        if (Producto && Producto.id) {
          this.appService.subirImg(this.imageSelected, Producto.id).subscribe(result => {
            this.matDialogClose(1, mensaje);
          });
        }
      }, error => {
        console.log(error.message);
      }
    )
  }

  submit() {
    switch (this.data.typeForm) {
      case 0:
        this.createProducto();
        break;
      case 1:
        this.updateProducto();
        break
      default:
        break;
    }
  }

  matDialogClose(tipo: number, message: string) {
    this.dialogRef.close({ tipo, message });
  }

  listarProveedores() {
    this.appService.get(`/proveedores/list`).subscribe(
      result => {
        this.proveedores = result;
      },
      error => {
        console.log(error.mensaje);
      }
    )
  }

  listUnidadesMedida() {
    this.appService.get(`/unidades/list`).subscribe(
      result => {
        this.unidadMedida = result;
      }, error => {
        console.log(error.mensaje);
      }
    )
  }

  onFileSelected(event) {
    this.imageSelected = event.target.files[0];
    const reader = new FileReader();
    reader.readAsDataURL(event.target.files[0]);
    reader.onload = (_event) => {
      this.imgUrl = reader.result;
    }
  }
}

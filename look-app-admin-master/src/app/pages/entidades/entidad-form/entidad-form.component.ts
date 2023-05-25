import { Component, OnInit, Input, Output, EventEmitter, Inject } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { AppService } from '../../../services/app.service';
import Swal from 'sweetalert2';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material';
import { ENDPOINTS } from '../../../appSettings';

@Component({
  // tslint:disable-next-line: component-selector
  selector: 'entidad-form',
  templateUrl: './entidad-form.component.html',
  styleUrls: ['./entidad-form.component.scss']
})
export class EntidadFormComponent implements OnInit {

  public datos: FormGroup;
  public imageSelected = null;
  public imgUrl: any;

  constructor(private formBuilder: FormBuilder, private appService: AppService,
    public dialogRef: MatDialogRef<EntidadFormComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any) {
    this.datos = this.formBuilder.group({
      nombre:  ['', Validators.required],
      contactEmail: ['', Validators.required],
      contactNombre:  ['', Validators.required],
      contactTelefono:  ['', Validators.required],
      direccion:  ['', Validators.required],
      estado:  [1, Validators.required],
      logoNombre: ['', Validators.required],
      nit:  ['', Validators.required],
      telefono: ['', Validators.required],
      ciudad: ['', Validators.required],
      pais: ['', Validators.required],
      codigoZip: ['', Validators.required],
      descripcion: ['', Validators.required]
    });
  }

  ngOnInit() {
    if (this.data.typeForm === 1 && this.data.data !== null) {
      this.datos.patchValue({
        ...this.data.data
      });
      this.imgUrl = ENDPOINTS.dev.uploads + this.data.data.logoNombre;
    }
  }

  setEntidad() {
    return {
      ...this.datos.value
    };
  }

  updateEntidad() {
    this.appService.put(`/entidad/${this.data.data.id}`, this.setEntidad()).subscribe(
      ({mensaje, entidad}) => {
        if (entidad && entidad.id) {
          this.appService.subirLogo(this.imageSelected, entidad.id).subscribe(result => {
            this.matDialogClose(1, mensaje);
          });
        }
      }, error => {
        console.log(error.message);
      }
    )
  }

  createEntidad() {
    this.appService.post(`/entidad/new`, this.setEntidad()).subscribe(
      ({mensaje, entidad}) => {
        if (entidad && entidad.id) {
          this.appService.subirLogo(this.imageSelected, entidad.id).subscribe(result => {
            this.matDialogClose(1, mensaje);
          });
          const userNew = this.setUsuario(entidad);
          this.appService.post(`/user/new`, userNew).subscribe();
        }
      }, error => {
        console.log(error.message);
      }
    )
  }

  setUsuario(entidad: any) {
    return {
      'apellidos': 'admin',
      'email': entidad.contactEmail,
      'enabled': true,
      'fecha_nacimiento': '2019-09-10',
      'idEntidad': {
        'id': entidad.id
      },
      'identificacion': entidad.nit,
      'nombres': 'admin',
      'password': entidad.nit,
      'roles': [
        {
          'id': 1,
          'nombre': 'ROLE_ADMIN',
          'privilegios': [
            {
              'id': 1,
              'nombre': 'READ_PRIVILEGE'
            },
            {
              'id': 2,
              'nombre': 'WRITE_PRIVILEGE'
            }
          ]
        }
      ],
      'telefono': entidad.telefono,
      'username': `admin_${entidad.nit}`
    }
  }

  submit() {
    switch (this.data.typeForm) {
      case 0:
        this.createEntidad();
        break;
      case 1:
        this.updateEntidad();
        break
      default:
        break;
    }
  }

  matDialogClose (tipo: number, message: string) {
    this.dialogRef.close({tipo, message});
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

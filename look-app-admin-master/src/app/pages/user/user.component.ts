import { Component, OnInit, AfterViewInit, AfterContentChecked } from '@angular/core';
import { AppService } from '../../services/app.service';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { ENDPOINTS } from '../../appSettings';
import Swal from 'sweetalert2';
import { NgxSmartModalService } from 'ngx-smart-modal';
import { HttpResponse, HttpEventType } from '@angular/common/http';

@Component({
  // tslint:disable-next-line: component-selector
  selector: 'user-cmp',
  moduleId: module.id,
  templateUrl: 'user.component.html'
})

export class UserComponent implements OnInit, AfterViewInit, AfterContentChecked {

  public datos: FormGroup;
  public idEntidad: any;
  public dataEntidad: any;
  public dataUsers: any;
  public imgUrl: any;
  public imageSelected = null;
  public puestos: any;
  public bienes: any;

  constructor(private formBuilder: FormBuilder, private appService: AppService, public ngxSmartModalServ: NgxSmartModalService) {
    this.datos = this.formBuilder.group({
      contactEmail:  ['', Validators.required],
      contactNombre:  ['', Validators.required],
      contactTelefono:  ['', Validators.required],
      direccion:  ['', Validators.required],
      estado:  [1, Validators.required],
      logoNombre:  ['', Validators.required],
      nit:  ['', Validators.required],
      nombre:  ['', Validators.required],
      telefono:  ['', Validators.required],
      ciudad: ['', Validators.required],
      pais: ['', Validators.required],
      codigoZip: ['', Validators.required],
      descripcion: ['', Validators.required]
    });
    const { entidad: { id } } = JSON.parse(sessionStorage.getItem('usuario'));
    this.idEntidad = id;
  }

  ngOnInit() {
    this.findEntidadById();
    this.findUsersByIdEntidad();
    this.listarBienes();
    this.listarPuestos();
  }

  ngAfterViewInit() { }

  ngAfterContentChecked() { }

  public findEntidadById() {
    this.appService.get(`/entidad/${this.idEntidad}`).subscribe(
      result => {
        this.dataEntidad = result;
        this.datos.patchValue({
          ...this.dataEntidad
        });
        this.imgUrl = ENDPOINTS.dev.uploads + this.dataEntidad.logoNombre;
      }
    );
  }

  public findUsersByIdEntidad() {
    this.appService.get(`/usuarios/entidad/${this.idEntidad}`).subscribe(
      result => {
        this.dataUsers = result;
      }
    );
  }

  public listarBienes() {
    this.appService.get(`/bienes/list`).subscribe(
      result => {
        this.bienes = result;
      },
      error => {
        console.log(error);
      }
    )
  }

  public listarPuestos() {
    this.appService.get(`/puestos/list`).subscribe(
      result => {
        this.puestos = result;
      },
      error => {
        console.log(error);
      }
    )
  }

  submit() {
    if (this.datos.valid) {
      this.updateEntidad();
    }
  }

  public updateEntidad() {
    this.appService.put(`/entidad/update/${this.idEntidad}`, this.datos.value).subscribe(
      result => {
        Swal.fire('Exito', result.mensaje, 'success');
      },
      error => {
       console.log(error.error);
      }
    )
  }

  public openFormSubirLogo() {
    this.ngxSmartModalServ.getModal('subirLogo').open();
  }

  public closeModal() {
    this.ngxSmartModalServ.getModal('subirLogo').close();
  }

  onFileSelected(event) {
    this.imageSelected = event.target.files[0];
    const reader = new FileReader();
    reader.readAsDataURL(event.target.files[0]);
    reader.onload = (_event) => {
      this.imgUrl = reader.result;
    }
  }

  subirImg() {
    this.appService.subirLogo(this.imageSelected, this.idEntidad).subscribe(
      event => {
        if (event.type === HttpEventType.Response) {
          const response: any = event.body;
          this.imgUrl = ENDPOINTS.dev.uploads + response.entidad.logoNombre;
          Swal.fire('Carga Exitosa!', response.mensaje , 'success');
          this.ngxSmartModalServ.getModal('subirLogo').close();
        }
      },
      error => {
        console.error(error.error);
      }
    )
  }
}

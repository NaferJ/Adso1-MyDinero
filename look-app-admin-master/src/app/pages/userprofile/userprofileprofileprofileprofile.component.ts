import { Component, OnInit, AfterViewInit, AfterContentChecked } from '@angular/core';
import { AppService } from '../../services/app.service';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { ENDPOINTS } from '../../appSettings';
import Swal from 'sweetalert2';
import { NgxSmartModalService } from 'ngx-smart-modal';
import { HttpResponse, HttpEventType } from '@angular/common/http';

@Component({
  // tslint:disable-next-line: component-selector
  selector: 'userprofileprofileprofileprofile-cmp',
  moduleId: module.id,
  templateUrl: 'userprofileprofileprofileprofile.component.html'
})

export class UserprofileprofileComponent implements OnInit, AfterViewInit, AfterContentChecked {

  public datos: FormGroup;
  public idEntidad: any;
  public idUsr: any;
  public dataUser: any;
  public imgUrl: any;
  public imageSelected = null;

  constructor(private formBuilder: FormBuilder, private appService: AppService, public ngxSmartModalServ: NgxSmartModalService) {
    this.datos = this.formBuilder.group({
      apellidos:  ['', Validators.required],
      direccion: ['', Validators.required],
      email:  ['', Validators.required],
      enabled:  [1, Validators.required],
      fecha_nacimiento: ['', Validators.required],
      identificacion: ['', Validators.required],
      imagen:  ['', Validators.required],
      nombres:  ['', Validators.required],
      telefono:  ['', Validators.required],
      username:  ['', Validators.required],
      password: ['$2a$10$Raahh.74lhQeexlgCYl6lOKghTUc2FIPKWI9kMQx.zxTu4Qk3vqDO']
    });
    const { entidad: { id } } = JSON.parse(sessionStorage.getItem('usuario'));
    const usuario = JSON.parse(sessionStorage.getItem('usuario'));
    this.idEntidad = id;
    this.idUsr = usuario.id;
  }

  ngOnInit() {
    console.log(this.idUsr);
    this.findUsrById();
  }

  ngAfterViewInit() { }

  ngAfterContentChecked() { }

  public findUsrById() {
    this.appService.get(`/usuario/${this.idUsr}`).subscribe(
      result => {
        this.dataUser = result;
        console.log(this.dataUser);
        this.datos.patchValue({
          ...this.dataUser
        });
        this.imgUrl = ENDPOINTS.dev.uploads + this.dataUser.imagen;
      }
    );
  }

  submit() {
    if (this.datos.valid) {
      this.updateUser();
    }
  }

  public updateUser() {
    this.appService.put(`/usuario/update/${this.idUsr}`, this.datos.value).subscribe(
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
    this.appService.subirImagen(this.imageSelected, this.idUsr).subscribe(
      event => {
        if (event.type === HttpEventType.Response) {
          const response: any = event.body;
          this.imgUrl = ENDPOINTS.dev.uploads + response.usuario.imagen;
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

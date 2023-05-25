import { Component, OnInit, Inject } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { AppService } from '../../../services/app.service';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material';
import { AuthService } from '../../../services/auth.service';

@Component({
  // tslint:disable-next-line: component-selector
  selector: 'unidad-form',
  templateUrl: './unidad-form.component.html',
  styleUrls: ['./unidad-form.component.scss']
})

export class UnidadFormComponent implements OnInit {

  private idEntidad: any;
  public datos: FormGroup;

  constructor(private formBuilder: FormBuilder, private appService: AppService,
    public dialogRef: MatDialogRef<UnidadFormComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any, public auth: AuthService) {

    this.datos = this.formBuilder.group(
      {
        nombre: ['', Validators.required],
        estado: [1, Validators.required],
        descripcion: ['', Validators.required]
      }
    );
  }

  ngOnInit() {
    const { entidad: { id } } = JSON.parse(sessionStorage.getItem('usuario'));
    this.idEntidad = id;

    if (this.data.typeForm === 1 && this.data.data !== null) {
      this.datos.patchValue({
        ...this.data.data
      });
    }
  }

  setUnidad() {
    return {
      ...this.datos.value
    };
  }

  updateUnidad() {
    this.appService.put(`/unidad/update/${this.data.data.id}`, this.setUnidad()).subscribe(
      result => {
        this.matDialogClose(1, result.mensaje);
      },
      error => {
        console.error(error.error);
      }
    );
  }

  createUnidad() {
    this.appService.post(`/unidad/new`, this.setUnidad()).subscribe(
      result => {
        this.matDialogClose(1, result.mensaje);
      },
      error => {
        console.log(error.error);
      }
    );
  }

  submit() {
    if (this.datos.valid) {
      switch (this.data.typeForm) {
        case 0:
          this.createUnidad();
          break;
        case 1:
          this.updateUnidad();
          break
        default:
          break;
      }
    }

  }

  matDialogClose(tipo: number, message: string) {
    this.dialogRef.close({ tipo, message });
  }

}

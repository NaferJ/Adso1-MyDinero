import { Component, OnInit, Inject } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { AppService } from '../../../services/app.service';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material';
import { AuthService } from '../../../services/auth.service';

@Component({
  selector: 'parametros-form',
  templateUrl: './parametros-form.component.html',
  styleUrls: ['./parametros-form.component.scss']
})

export class ParametrosFormComponent implements OnInit {

  private idEntidad: any;
  public datos: FormGroup;
  public grupo: any;

  constructor(private formBuilder: FormBuilder, private appService: AppService,
    public dialogRef: MatDialogRef<ParametrosFormComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any, public auth: AuthService) {
      this.listarGrupos();
    this.datos = this.formBuilder.group(
      {
        nombre: ['', Validators.required],
        abrev: ['', Validators.required],
        fechaInicio: ['', Validators.required],
        fechaFin: ['', Validators.required],
        codigo: ['', Validators.required],
        valor: ['', Validators.required],
        grupo: ['' , Validators.required]
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

  listarGrupos() {
    this.appService.get(`/grupos/list`).subscribe(
      result => {
        this.grupo = result;
      },
      error => {
        console.log(error.mensaje);
      }
    )
  }

  setParametro() {
    return {
      ...this.datos.value,
      enditad: {
        id: this.idEntidad
      },
      grupo: {
        id: this.datos.value.grupo
      }
    };
  }

  updateParametro() {
    console.log(this.setParametro());
    this.appService.put(`/parametro/update/${this.data.data.id}`, this.setParametro()).subscribe(
      result => {
        this.matDialogClose(1, result.mensaje);
      },
      error => {
        console.error(error.error);
      }
    );
  }

  createParametro() {
    console.log(this.setParametro());
    this.appService.post(`/parametro/new`, this.setParametro()).subscribe(
      result => {
        this.matDialogClose(1, result.mensaje);
      },
      error => {
        console.log(error.error);
      }
    );
  }

  submit() {
    switch (this.data.typeForm) {
      case 0:
        this.createParametro();
        break;
      case 1:
        this.updateParametro();
        break
      default:
        break;
    }
  }

  matDialogClose(tipo: number, message: string) {
    this.dialogRef.close({ tipo, message });
  }

}

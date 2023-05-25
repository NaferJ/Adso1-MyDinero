import { Component, OnInit, Inject } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { AppService } from '../../../services/app.service';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material';
import { AuthService } from '../../../services/auth.service';

@Component({
  // tslint:disable-next-line: component-selector
  selector: 'grupos-form',
  templateUrl: './grupos-form.component.html',
  styleUrls: ['./grupos-form.component.scss']
})

export class GruposFormComponent implements OnInit {

  private idEntidad: any;
  public datos: FormGroup;

  constructor(private formBuilder: FormBuilder, private appService: AppService,
    public dialogRef: MatDialogRef<GruposFormComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any, public auth: AuthService) {

    this.datos = this.formBuilder.group(
      {
        nombre: ['', Validators.required]
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

  setGrupo() {
    return {
      ...this.datos.value
    };
  }

  updateGrupo() {
    this.appService.put(`/grupo/update/${this.data.data.id}`, this.setGrupo()).subscribe(
      result => {
        this.matDialogClose(1, result.mensaje);
      },
      error => {
        console.error(error.error);
      }
    );
  }

  createGrupo() {
    this.appService.post(`/grupo/new`, this.setGrupo()).subscribe(
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
          this.createGrupo();
          break;
        case 1:
          this.updateGrupo();
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

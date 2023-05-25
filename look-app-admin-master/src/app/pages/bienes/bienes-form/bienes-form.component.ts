import { Component, OnInit, Inject } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { AppService } from '../../../services/app.service';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material';
import { AuthService } from '../../../services/auth.service';

@Component({
  // tslint:disable-next-line: component-selector
  selector: 'bienes-form',
  templateUrl: './bienes-form.component.html',
  styleUrls: ['./bienes-form.component.scss']
})

export class BienesFormComponent implements OnInit {

  private idEntidad: any;
  public datos: FormGroup;

  constructor(private formBuilder: FormBuilder, private appService: AppService,
    public dialogRef: MatDialogRef<BienesFormComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any, public auth: AuthService) {

    this.datos = this.formBuilder.group(
      {
        nombres: ['', Validators.required],
        valor: ['', Validators.required],
        estado: [1, Validators.required]
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

  setBien() {
    return {
      ...this.datos.value,
      idEntidad: {
        id: this.idEntidad
      }
    };
  }

  updateBien() {
    this.appService.put(`/bienservicio/update/${this.data.data.id}`, this.setBien()).subscribe(
      result => {
        this.matDialogClose(1, result.mensaje);
      },
      error => {
        console.error(error.error);
      }
    );
  }

  createBien() {
    this.appService.post(`/bienservicio/new`, this.setBien()).subscribe(
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
          this.createBien();
          break;
        case 1:
          this.updateBien();
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

import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';

import { LoginLayoutRoutes } from './Login-layout.routing';

import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { AuthComponent } from '../../pages/auth/auth.component';
import { SweetAlert2Module } from '@sweetalert2/ngx-sweetalert2';
import { NgxSpinnerModule } from 'ngx-spinner';

@NgModule({
  imports: [
    CommonModule,
    RouterModule.forChild(LoginLayoutRoutes),
    FormsModule,
    NgbModule,
    NgxSpinnerModule,
    HttpClientModule,
    SweetAlert2Module.forRoot()
  ],
  declarations: [
    AuthComponent
  ]
})

export class LoginLayoutModule {}

import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { LoginComponent } from './components/login/login.component';
import {AppMaterialModule} from 'src/app/modules/app-material/app-material.module';
import {AppRoutingModule} from 'src/app/app-routing.module';
import { FlexLayoutModule } from "@angular/flex-layout";
import { RegisterComponent } from './components/register/register.component';


@NgModule({
  declarations: [LoginComponent, RegisterComponent],
  imports: [
    CommonModule,
    AppMaterialModule,
    AppRoutingModule,
    FlexLayoutModule
  ]
})
export class AuthenticationModule { }

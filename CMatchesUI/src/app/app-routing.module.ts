import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {LoginComponent} from 'src/app/modules/authentication/components/login/login.component';
import {RegisterComponent} from 'src/app/modules/authentication/components/register/register.component';
import {MatchesComponent} from 'src/app/modules/matches/components/matches/matches.component';
import {AuthGuardService} from 'src/app/modules/matches/auth-guard.service';

const routes: Routes = [
  {
    path:"",
    component:LoginComponent
  },
  {
    path:"register",
    component:RegisterComponent
  },
  {
    path: "login",
    component : LoginComponent
  },
  {
    path: "matches",
    component : MatchesComponent,
    canActivate : [AuthGuardService]
  },
  {
    path: "favourites",
    component : MatchesComponent,
    canActivate : [AuthGuardService]
  },
  {
    path: "recommendations",
    component : MatchesComponent,
    canActivate : [AuthGuardService]
  }
]
@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
  
 }

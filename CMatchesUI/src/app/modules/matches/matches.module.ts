import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {AppMaterialModule} from 'src/app/modules/app-material/app-material.module';
import {AppRoutingModule} from 'src/app/app-routing.module';
import { FlexLayoutModule } from "@angular/flex-layout";
import { MatchesComponent } from './components/matches/matches.component';
import {MatchesService} from 'src/app/modules/matches/matches.service';
import { HTTP_INTERCEPTORS } from '@angular/common/http';
import {InterceptorService} from 'src/app/modules/matches/interceptor.service';
import { ScoreComponent } from './components/score/score.component';

@NgModule({
  declarations: [MatchesComponent, ScoreComponent],
  imports: [
    CommonModule,
    AppMaterialModule,
    AppRoutingModule,
    FlexLayoutModule
  ],
  entryComponents: [
    ScoreComponent
  ],
  providers :[
    MatchesService,
    {
      provide : HTTP_INTERCEPTORS,
      useClass :InterceptorService,
      multi : true
    }
  ],
})
export class MatchesModule { }

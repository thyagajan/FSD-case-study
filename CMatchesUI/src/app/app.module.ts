import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import {AppMaterialModule} from 'src/app/modules/app-material/app-material.module';
import {AuthenticationModule} from 'src/app/modules/authentication/authentication.module';
import {MatchesModule} from 'src/app/modules/matches/matches.module';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HeaderComponent } from './header/header.component';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import { FooterComponent } from './footer/footer.component';
import { FlexLayoutModule } from "@angular/flex-layout";
import { HttpClientModule } from '@angular/common/http';


@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    FooterComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    AppMaterialModule,
    BrowserAnimationsModule,AuthenticationModule,
    FlexLayoutModule,
    HttpClientModule,
    MatchesModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }

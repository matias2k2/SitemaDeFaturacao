import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginfromComponent } from './Components/loginfrom/loginfrom.component';
import { SiginComponent } from './Components/sigin/sigin.component';
import { HomeComponent } from './Components/home/home.component';
import { FromularioComponent } from './Components/fromulario/fromulario.component';

@NgModule({
  declarations: [
    AppComponent,
    LoginfromComponent,
    SiginComponent,
    HomeComponent,
    FromularioComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }

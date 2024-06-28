import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginfromComponent } from './Components/loginfrom/loginfrom.component';
import { SiginComponent } from './Components/sigin/sigin.component';
import { HomeComponent } from './Components/home/home.component';
import { FromularioComponent } from './Components/fromulario/fromulario.component';
import {HttpClientModule} from '@angular/common/http';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MarcasComponent } from './Components/marcas/marcas.component';
import { DashbordComponent } from './Components/dashbord/dashbord.component';
import { ProdutosComponent } from './Components/produtos/produtos.component';
import { ListaProdutosComponent } from './Components/lista-produtos/lista-produtos.component';
import { HeaderComponent } from './Components/header/header/header.component';
import { InicioComponent } from './Components/inicio/inicio.component';
import { MenuComponent } from './Components/menu/menu.component';


@NgModule({
  declarations: [
    AppComponent,
    LoginfromComponent,
    SiginComponent,
    HomeComponent,
    FromularioComponent,
    MarcasComponent,
    DashbordComponent,
    ProdutosComponent,
    ListaProdutosComponent,
    HeaderComponent,
    InicioComponent,
    MenuComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpClientModule,
    ReactiveFormsModule,
    AppRoutingModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }

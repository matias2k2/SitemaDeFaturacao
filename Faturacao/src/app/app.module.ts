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
import { CategoriaComponent } from './Components/categoria/categoria.component';
import { CommonModule } from '@angular/common';

import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

import { ToastrModule } from 'ngx-toastr';
import { FatuaraFromComponent } from './Components/fatuara-from/fatuara-from.component';
import { AdminComponent } from './Components/admin/admin.component';


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
    MenuComponent,
    CategoriaComponent,
    FatuaraFromComponent,
    AdminComponent,
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpClientModule,
    ReactiveFormsModule,
    AppRoutingModule,
    CommonModule,
    BrowserAnimationsModule, // required animations module
    ToastrModule.forRoot()
  ],
  providers: [],
  bootstrap: [AppComponent],
})
export class AppModule {}

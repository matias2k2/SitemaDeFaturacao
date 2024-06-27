import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginfromComponent } from './Components/loginfrom/loginfrom.component';
import { SiginComponent } from './Components/sigin/sigin.component';
import { HomeComponent } from './Components/home/home.component';
import { DashbordComponent } from './Components/dashbord/dashbord.component';
import { ProdutosComponent } from './Components/produtos/produtos.component';

const routes: Routes = [
    {path : 'login', component : LoginfromComponent},
    {path : 'signup', component : SiginComponent},
    {path : '', component : HomeComponent},
    {path : 'dashbor', component : DashbordComponent},
    {path : 'produtos', component : ProdutosComponent}


];


@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

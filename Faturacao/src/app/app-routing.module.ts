import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginfromComponent } from './Components/loginfrom/loginfrom.component';
import { SiginComponent } from './Components/sigin/sigin.component';
import { HomeComponent } from './Components/home/home.component';

const routes: Routes = [
    {path : 'login', component : LoginfromComponent},
    {path : 'signup', component : SiginComponent},
    {path : '', component : HomeComponent}



];


@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

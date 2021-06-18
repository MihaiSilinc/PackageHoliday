import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {HttpClientModule} from '@angular/common/http';
import {FormsModule} from '@angular/forms';

import {AppComponent} from './app.component';
import {AppRoutingModule} from "./app-routing.module";

import {HotelsShowComponent} from './hotels/hotels-show/hotels-show.component';
import {HotelsAddComponent} from './hotels/hotels-add/hotels-add.component';
import {HotelsDeleteComponent} from './hotels/hotels-remove/hotels-remove.component';
import {HotelsUpdateComponent} from './hotels/hotels-update/hotels-update.component';

import {CustomerAddComponent} from './customers/customers-add/customers-add.component';
import {CustomerDeleteComponent} from './customers/customers-remove/customers-remove.component';
import {CustomerShowComponent} from './customers/customers-show/customers-show.component';
import {CustomerUpdateComponent} from './customers/customers-update/customers-update.component';

import { PackagesShowComponent} from "./packageholiday/package-show/package-show.component";

import {MenuComponent} from './menu/menu-component';
import {ReactiveFormsModule} from "@angular/forms";
import {PackageHoliday} from "./packageholiday/package-model";



@NgModule({
  declarations: [
    AppComponent,
    HotelsShowComponent,
    HotelsAddComponent,
    HotelsDeleteComponent,
    HotelsUpdateComponent,
    MenuComponent,
    CustomerAddComponent,
    CustomerDeleteComponent,
    CustomerShowComponent,
    CustomerUpdateComponent,
    PackagesShowComponent,

  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpClientModule,
    AppRoutingModule,
    ReactiveFormsModule,
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {
}

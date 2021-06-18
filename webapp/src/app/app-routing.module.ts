import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {HotelsShowComponent} from "./hotels/hotels-show/hotels-show.component";
import {HotelsAddComponent} from "./hotels/hotels-add/hotels-add.component";
import {HotelsDeleteComponent} from "./hotels/hotels-remove/hotels-remove.component";
import {HotelsUpdateComponent} from "./hotels/hotels-update/hotels-update.component";
import {CustomerAddComponent} from './customers/customers-add/customers-add.component';
import {CustomerDeleteComponent} from './customers/customers-remove/customers-remove.component';
import {CustomerShowComponent} from './customers/customers-show/customers-show.component';
import {CustomerUpdateComponent} from './customers/customers-update/customers-update.component';
import {PackagesShowComponent} from "./packageholiday/package-show/package-show.component";

import {MenuComponent} from "./menu/menu-component";


const routes: Routes = [
  { path: '', redirectTo: 'menu', pathMatch: 'full' },
  {path: 'menu', component: MenuComponent},
  {path: 'showHotels', component: HotelsShowComponent},
  {path: 'addHotel', component: HotelsAddComponent},
  {path: 'deleteHotel', component: HotelsDeleteComponent},
  {path: 'updateHotel', component: HotelsUpdateComponent},
  {path: 'showCustomers', component: CustomerShowComponent},
  {path: 'addCustomer', component: CustomerAddComponent},
  {path: 'deleteCustomer', component: CustomerDeleteComponent},
  {path: 'updateCustomer', component: CustomerUpdateComponent},
  {path: 'showPackages', component: PackagesShowComponent}


];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}

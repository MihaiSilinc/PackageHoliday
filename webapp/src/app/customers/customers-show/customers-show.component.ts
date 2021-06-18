import { Component, OnInit } from '@angular/core';
import {Router} from "@angular/router";
import {Customer} from "../customers-model";
import {CustomersService} from "../customers-service";

@Component({
  selector: 'app-customer-show',
  templateUrl: './customers-show.component.html',
  styleUrls: ['./customers-show.component.css']
})
export class CustomerShowComponent implements OnInit {

  customers: Array<Customer>;

  constructor(private service: CustomersService, private router: Router) {
  }

  ngOnInit(): void {
    this.refresh("");
  }

  refresh(name: string): void {
    this.service.getCustomersByName(name).subscribe(customers => this.customers = customers.customers);
  }

  navigateToDelete(customerId: number): void {
    this.router.navigate(['deleteCustomer'], {queryParams: {id: customerId}}).then(_ => {
    });
  }

  navigateToAdd(): void {
    this.router.navigate(['addCustomer']).then(_ => {
    });
  }

  navigateToUpdate(customerId: number): void {
    this.router.navigate(['updateCustomer'], {queryParams: {id: customerId}}).then(_ => {
    });
  }

  onBack() {
    this.router.navigate(['menu']).then(_ => {
    })
  }
}

import { Component, OnInit } from '@angular/core';
import {Router} from "@angular/router";
import {CustomersService} from "../customers-service";

@Component({
  selector: 'app-customer-add',
  templateUrl: './customers-add.component.html',
  styleUrls: ['./customers-add.component.css']
})
export class CustomerAddComponent implements OnInit {

  constructor(private service: CustomersService, private router: Router) {
  }

  ngOnInit(): void {
  }

  addCustomer(data): void {
    if (!new RegExp("^[0-9]{2}$").test(data.age)) {
      alert("Age invalid")
      return
    }
    this.service.addCustomer(data.name, data.age).subscribe(() => {
      this.router.navigate(['showCustomers']).then(_ => {
      });
    });
  }

  onCancel(): void {
    this.router.navigate(['showCustomers']).then(_ => {
    });
  }
}

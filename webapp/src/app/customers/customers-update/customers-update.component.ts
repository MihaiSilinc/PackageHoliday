import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {CustomersService} from "../customers-service";

@Component({
  selector: 'app-customer-update',
  templateUrl: './customers-update.component.html',
  styleUrls: ['./customers-update.component.css']
})
export class CustomerUpdateComponent implements OnInit {

  name = ""
  age = 0

  constructor(private service: CustomersService, private router: Router, private route: ActivatedRoute) {
  }

  ngOnInit(): void {
    this.service.getCustomer(this.route.snapshot.queryParams.id).subscribe(customer => {
      this.name = customer.name
      this.age = customer.age
    })
  }

  updateCustomer(data): void {
    if (!new RegExp("^[0-9]{2}$").test(data.age)) {
      alert("Age invalid")
      return
    }
    const id = this.route.snapshot.queryParams.id;
    this.service.updateCustomer(id, data.name, data.age).subscribe(() => {
      this.router.navigate(['showCustomers']).then(_ => {
      });
    });
  }

  onCancel(): void {
    this.router.navigate(['showCustomers']).then(_ => {
    });
  }

}

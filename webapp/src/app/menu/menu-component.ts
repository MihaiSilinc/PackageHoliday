import {Component, OnInit} from '@angular/core';
import {Router} from "@angular/router";

@Component({
  selector: 'app-menu',
  templateUrl: './menu-component.html',
  styleUrls: ['./menu-component.css']
})
export class MenuComponent implements OnInit {

  constructor(private router: Router) {
  }

  ngOnInit(): void {
  }

  onHotels() {
    this.router.navigate(['showHotels']).then(_ => {
    })
  }
  onCustomers() {
    this.router.navigate(['showCustomers']).then(_ => {
    })
  }
  onPackages() {
    this.router.navigate(['showPackages']).then(_ => {
    })
  }

}

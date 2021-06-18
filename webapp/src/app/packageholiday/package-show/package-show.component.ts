import { Component, OnInit } from '@angular/core';
import {Router} from "@angular/router";
import {PackageHoliday} from "../package-model";
import {PackagesService} from "../package-service";

@Component({
  selector: 'app-customer-show',
  templateUrl: './package-show.component.html',
  styleUrls: ['./package-show.component.css']
})
export class PackagesShowComponent implements OnInit {

  packages: Array<PackageHoliday>;

  constructor(private service: PackagesService, private router: Router) {
  }

  ngOnInit(): void {
    this.refresh();
  }

  refresh(): void {
    this.service.getPackages().subscribe(packages => this.packages = packages.packages);
  }


  onBack() {
    this.router.navigate(['menu']).then(_ => {
    })
  }
}

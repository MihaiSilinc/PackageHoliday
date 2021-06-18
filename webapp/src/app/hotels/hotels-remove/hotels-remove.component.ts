import { Component, OnInit } from '@angular/core';
import {HotelsService} from "../hotels-service";
import {ActivatedRoute, Router} from "@angular/router";

@Component({
  selector: 'app-hotels-remove',
  templateUrl: './hotels-remove.component.html',
  styleUrls: ['./hotels-remove.component.css']
})
export class HotelsDeleteComponent implements OnInit {

  constructor(private service: HotelsService, private router: Router, private route: ActivatedRoute) {
  }

  ngOnInit(): void {
  }

  onYes(): void {
    this.service.deleteHotel(this.route.snapshot.queryParams.id).subscribe(() => {
      this.router.navigate(['showHotels']).then(_ => {
      });
    });
  }

  onNo(): void {
    this.router.navigate(['showHotels']).then(_ => {
    });
  }

}

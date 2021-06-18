import {Component, OnInit} from '@angular/core';
import {Router} from "@angular/router";
import {Hotel} from "../hotels-model";
import {HotelsService} from "../hotels-service";

@Component({
  selector: 'app-hotels-show',
  templateUrl: './hotels-show.component.html',
  styleUrls: ['./hotels-show.component.css']
})
export class HotelsShowComponent implements OnInit {

  hotels: Array<Hotel>;

  constructor(private service: HotelsService, private router: Router) {
  }

  ngOnInit(): void {
    this.refresh()
  }

  refresh(): void {
    this.service.getHotels().subscribe(cats => this.hotels = cats.hotels);
  }

  navigateToDelete(hotelId: number): void {
    this.router.navigate(['deleteHotel'], {queryParams: {id: hotelId}}).then(_ => {
    });
  }

  navigateToAdd(): void {
    this.router.navigate(['addHotel']).then(_ => {
    });
  }

  navigateToUpdate(hotelId: number): void {
    this.router.navigate(['updateHotel'], {queryParams: {id: hotelId}}).then(_ => {
    });
  }

  onBack() {
    this.router.navigate(['menu']).then(_ => {
    })
  }

}

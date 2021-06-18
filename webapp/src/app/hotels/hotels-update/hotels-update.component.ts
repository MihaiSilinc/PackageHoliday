import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {HotelsService} from "../hotels-service";

@Component({
  selector: 'app-hotels-update',
  templateUrl: './hotels-update.component.html',
  styleUrls: ['./hotels-update.component.css']
})
export class HotelsUpdateComponent implements OnInit {

  name = ""
  location = ""
  stars = 0
  capacity = 0

  constructor(private service: HotelsService, private router: Router, private route: ActivatedRoute) {
  }

  ngOnInit(): void {
    this.service.getHotel(this.route.snapshot.queryParams.id).subscribe(hotel => {
      this.name = hotel.name
      this.location = hotel.location
      this.stars = hotel.stars
      this.capacity = hotel.capacity
    })
  }

  updateHotel(data): void {
    const id = this.route.snapshot.queryParams.id;
    this.service.updateHotel(id, data.name, data.location, data.stars, data.capacity).subscribe(() => {
      this.router.navigate(['showHotels']).then(_ => {
      });
    });
  }

  onCancel(): void {
    this.router.navigate(['showHotels']).then(_ => {
    });
  }


}

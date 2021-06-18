import {Component, OnInit} from '@angular/core';
import {HotelsService} from "../hotels-service";
import {Router} from "@angular/router";
import {FormControl, FormGroup, Validators} from "@angular/forms";

@Component({
  selector: 'app-hotels-add',
  templateUrl: './hotels-add.component.html',
  styleUrls: ['./hotels-add.component.css']
})
export class HotelsAddComponent implements OnInit {
  addHotelForm = new FormGroup({
    name: new FormControl('', Validators.required),
    location: new FormControl('', Validators.required),
    stars: new FormControl('', Validators.required),
    capacity: new FormControl('', Validators.required),
  })

  constructor(private service: HotelsService, private router: Router) {
  }

  ngOnInit(): void {
  }

  addHotel(): void {
    if (this.addHotelForm.get("name").invalid) {
      alert("The hotel name cannot be empty")
      return
    }
    if (this.addHotelForm.get("location").invalid) {
      alert("The hotel location cannot be empty")
      return
    }
    if (this.addHotelForm.get("stars").invalid) {
      alert("The hotel stars cannot be empty")
      return
    }
    if (this.addHotelForm.get("capacity").invalid) {
      alert("The hotel capacity cannot be empty")
      return
    }
    this.service.addHotel(this.addHotelForm.get("name").value, this.addHotelForm.get("location").value, this.addHotelForm.get("stars").value, this.addHotelForm.get("capacity").value).subscribe(() => {
      this.router.navigate(['showHotels']).then(_ => {
      });
    });
  }

  onCancel(): void {
    this.router.navigate(['showHotels']).then(_ => {
    });
  }

}

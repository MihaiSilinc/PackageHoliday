import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from "rxjs";
import {Hotel, HotelsDTO} from "./hotels-model";
import {map} from "rxjs/operators";

@Injectable({
  providedIn: 'root'
})
export class HotelsService {
  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json'
    })
  };
  private backendUrl = 'http://localhost:8080/api/hotels';

  constructor(private http: HttpClient) {
  }

  getHotels(): Observable<HotelsDTO> {
    return this.http.get<HotelsDTO>(this.backendUrl);
  }

  getHotel(hotelId: number): Observable<Hotel> {
    return this.getHotels().pipe(
      map(hotels => hotels.hotels.find(hotel => hotel.id == hotelId))
    );
  }

  addHotel(name: string, location: string, stars: number, capacity: number): Observable<any> {
    return this.http.post(this.backendUrl, new Hotel(name, location, stars, capacity));
  }

  deleteHotel(HotelId: number): Observable<any> {
    return this.http.delete(this.backendUrl + `/${HotelId}`);
  }

  updateHotel(HotelId: number, newName: string, newLocation: string, newStars: number, newCapacity: number): Observable<any> {
    return this.http.put(this.backendUrl + `/${HotelId}`, {
      name: newName,
      location: newLocation,
      stars: newStars,
      capacity: newCapacity
    });
  }
}

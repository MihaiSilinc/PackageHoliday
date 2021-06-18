import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from "rxjs";
import {PackageHoliday, PackageDTO} from "./package-model";
import {map} from "rxjs/operators";

@Injectable({
  providedIn: 'root'
})
export class PackagesService {
  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json'
    })
  };
  private backendUrl = 'http://localhost:8080/api/packages';

  constructor(private http: HttpClient) {
  }

  getPackages(): Observable<PackageDTO> {
    return this.http.get<PackageDTO>(this.backendUrl);
  }

}


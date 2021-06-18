import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from "rxjs";
import {Customer, CustomersDTO} from "./customers-model";
import {map} from "rxjs/operators";

@Injectable({
  providedIn: 'root'
})
export class CustomersService {
  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json'
    })
  };
  private backendUrl = 'http://localhost:8080/api/customers';

  constructor(private http: HttpClient) {
  }

  getCustomers(): Observable<CustomersDTO> {
    return this.http.get<CustomersDTO>(this.backendUrl);
  }

  getCustomer(customerId: number): Observable<Customer> {
    return this.getCustomers().pipe(
      map(customers => customers.customers.find(customer => customer.id == customerId))
    );
  }

  getCustomersByName(name: string): Observable<CustomersDTO> {
    return this.http.get<CustomersDTO>(this.backendUrl + `/${name}`, {});
  }

  addCustomer(name: string, age: number): Observable<any> {
    return this.http.post(this.backendUrl, new Customer(name, age));
  }

  deleteCustomer(CustomerId: number): Observable<any> {
    return this.http.delete(this.backendUrl + `/${CustomerId}`);
  }

  updateCustomer(CustomerId: number, newName: string, newAge: number): Observable<any> {
    return this.http.put(this.backendUrl + `/${CustomerId}`, {
      name: newName,
      age: newAge
    });
  }
}

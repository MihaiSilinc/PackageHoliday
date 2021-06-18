export class Customer {
  id: number;
  name: string;
  age: number;

  constructor(name: string = "", age: number = 0) {
    this.name = name;
    this.age = age;
  }
}

export class CustomersDTO {
  customers: Array<Customer>
}

export class PackageHoliday {
  idClient: number;
  idHotel:number;
  startdate: string;
  enddate: string;
  price: number;

  constructor(startdate: string = "", enddate: string = "", price: number = 0) {
    this.startdate = startdate;
    this.enddate = enddate;
    this.price = price;
  }
}

export class PackageDTO {
  packages: Array<PackageHoliday>
}

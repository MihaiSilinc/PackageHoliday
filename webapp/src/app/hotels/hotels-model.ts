export class Hotel{
  id: number;
  name: string;
  location: string;
  stars: number;
  capacity: number;

  constructor(name: string = "", location: string = "", stars: number = 0, capacity: number = 0) {
    this.name = name;
    this.location = location;
    this.stars = stars;
    this.capacity = capacity;
  }
}

export class HotelsDTO {
  hotels: Array<Hotel>
}

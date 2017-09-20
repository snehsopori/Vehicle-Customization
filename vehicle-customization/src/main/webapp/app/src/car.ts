export class Car {
    vin:number;
    make:string;
    model:string;
    year:number;
    trim:string;

    constructor(vin:number, make: string, model: string, year: number, trim: string) {
        this.make = make;
        this.model = model;
        this.year = year;
        this.trim = trim;
        this.vin = vin;
    }
}
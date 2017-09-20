export class Customer{

    name:string = "";
    email:string = "";
    phone:string = "";
    city:string = "";


    constructor(name: string, email: string, phone: string, city: string) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.city = city;
    }
}
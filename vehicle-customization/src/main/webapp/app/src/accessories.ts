export class Accessories{

    accessoryName:string = "";
    accessoryPrice:number = 0;
    image:string = "";

    constructor(name: string, price: number, image: string) {
        this.accessoryName = name;
        this.accessoryPrice = price;
        this.image = image;
    }
}
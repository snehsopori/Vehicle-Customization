import {Component, OnInit} from "@angular/core";
import {Transaction} from "./transaction";
import {ActivatedRoute, Router} from "@angular/router";
import {Headers, Http, RequestOptions} from "@angular/http";
import {Customer} from "./customer";
import {Car} from "./car";
import {Accessories} from "./accessories";


@Component({
    template: `
        <div *ngIf="customer">
            <h1>Your transaction details are : </h1>
            <img [width]='imageSize.w' [height]='imageSize.h' src='images/car.jpg'/>
            <h2>Customer Details : </h2>
            <h3>Name : {{customer.name}}</h3>
            <h3>Email : {{customer.email}}</h3>
            <h3>Phone : {{customer.phone}}</h3>
            <h3>City : {{customer.city}}</h3>
        </div>
        <div *ngIf="car">
            <h2>Car Details : </h2>
            <h3>Make : {{car.make}}, Model : {{car.model}}, Year : {{car.year}}, Trim : {{car.trim}}</h3>
        </div>
        <div *ngIf="accessories">
            <h2>Accessory Details : </h2>
            <ul *ngFor="let acc of accessories">
                <li>Accessory Name : {{acc.accessoryName}}, Accessory Price : {{acc.accessoryPrice}}</li>
            </ul>
        </div>
        <div>
            <h2>Total Bill : {{totalPrice}}</h2>
        </div>
        <div>
            <input type="submit" value="Home" name="Home" (click)="backToHome();">
        </div>`,

    selector: 'tdetails-app'
})
export class TransactionDetailComponent {

    totalPrice: number;
    transaction: Transaction;
    tid: number;
    customer: Customer;
    car: Car;
    aidList: [number];
    accessory: Accessories;
    accessories: Accessories[] = [];
    vin: number;

    imageSize = {w: 496, h: 206};

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private http: Http) {
        this.activatedRoute.params.subscribe((prms) => {
            this.tid = prms['tid'];
            this.totalPrice = prms['bill'];
            this.vin = prms['carId'];
            console.log(this.tid);
            console.log(this.totalPrice);
        });
        var searchURL = "";

        searchURL = "/transactionDetails/" + this.tid;

        var requestHeaders = new Headers({'Accept': 'application/json'});
        var options = new RequestOptions({headers: requestHeaders});

        this.http.get(searchURL, options).subscribe(res => {

            this.transaction = res.json();
            console.log(this.transaction);
            var searchURL = "";

            searchURL = "/customer/" + this.transaction.cid;

            var requestHeaders = new Headers({'Accept': 'application/json'});
            var options = new RequestOptions({headers: requestHeaders});

            this.http.get(searchURL, options).subscribe(res => {

                this.customer = res.json();
                console.log(this.customer);
            });

            searchURL = "/incrementor/" + this.tid;
            this.http.get(searchURL, options).subscribe(res => {

                this.aidList = res.json();
                console.log(this.aidList);

                for (var i = 0; i < this.aidList.length; i++) {
                    var searchURL = "";
                    searchURL = "/acc/" + this.aidList[i];
                    var requestHeaders = new Headers({'Accept': 'application/json'});
                    var options = new RequestOptions({headers: requestHeaders});
                    this.http.get(searchURL, options).subscribe(res => {

                        this.accessory = res.json();
                        console.log(this.accessory);
                        this.accessories.push(this.accessory);
                    });
                }

                console.log(this.accessories);
                console.log(this.aidList[0]);


                searchURL = "/car/" + this.vin;
                var requestHeaders = new Headers({'Accept': 'application/json'});
                var options = new RequestOptions({headers: requestHeaders});
                this.http.get(searchURL, options).subscribe(res => {

                    console.log(res.json());
                    this.car = res.json();

                });

            });
        });
    }

    backToHome() {
        this.router.navigate(["/home"]);
    }
}
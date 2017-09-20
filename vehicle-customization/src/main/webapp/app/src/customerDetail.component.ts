import {Component} from "@angular/core";
import {Headers, Http, RequestOptions} from "@angular/http";
import {Customer} from "./customer";
import {ActivatedRoute, Router} from "@angular/router";

@Component({
    templateUrl: '../partials/customerDetail.component.html',
    styleUrls: ['../css/customerDetail.component.css'],
    selector: 'customer-app'
})
export class CustomerDetailComponent {

    header: string = "CustomerDetails";

    customer: Customer;
    id: number;
    cid: number;
    bill:number = 0;
    carId:number = 0;

    successMessage: string;
    errorMessage: string;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private http: Http) {
        this.activatedRoute.params.subscribe((prms) => {
            this.id = prms['tid'];
            this.bill = prms['bill'];
            this.carId = prms['carId']
            console.log("Tid is : " + this.id + " *****  Bill is : " + this.bill);
        });
        this.customer = new Customer("", "", "", "");
    }

    addCustomer() {
        console.log("Inside addCustomer()!!!!");
        let addUrl = "/addCustomer";

        var requestHeaders = new Headers({'Content-Type': 'application/json'});
        var options = new RequestOptions({headers: requestHeaders});

        this.http.post(addUrl, this.customer, options).subscribe(
            res => {
                this.cid = res.json();
                let updateUrl = "/updateTransaction/" + this.id + "/" + this.cid;

                var requestHeaders = new Headers({'Content-Type': 'application/json'});
                var options = new RequestOptions({headers: requestHeaders});

                this.http.put(updateUrl, options).subscribe(
                    res => {
                        this.successMessage = res.toString();
                        console.log(res.text());
                        this.errorMessage = ""
                        this.router.navigate(['transaction-detail/', this.carId, this.id, this.bill]);
                    },
                    error => {
                        this.errorMessage = <any>error;
                        this.successMessage = ""
                    });
                this.successMessage = res.toString();
                this.errorMessage = ""
            },
            error => {
                this.errorMessage = <any>error;
                this.successMessage = ""
            });
    }
}

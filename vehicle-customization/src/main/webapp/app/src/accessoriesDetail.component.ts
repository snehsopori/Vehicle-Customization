///<reference path="transaction.ts"/>
import {Component} from "@angular/core";
import {ActivatedRoute, Router} from "@angular/router";
import {Headers, Http, RequestOptions} from "@angular/http";
import {Accessories} from "./accessories";
import {createUrlResolverWithoutPackagePrefix} from "@angular/compiler/src/url_resolver";
import {Transaction} from "./transaction";
import {Incrementor} from "./incrementor";

@Component({
    templateUrl:'../partials/accessoriesDetail.component.html',
    styleUrls:['../css/accessoriesDetail.component.css'],
    selector:'accessory-app'
})
export class AccessoriesDetailComponent{
    result:number = 0;
    header:string = "Select the accessories";
    accessories:[Accessories];
    id:number;
    successMessage:string;
    errorMessage:string;
    transaction:Transaction;
    incrementor : Incrementor ;
    incrementorSet: Set<Incrementor>;
    tid:number;

    constructor(private activatedRoute:ActivatedRoute, private router: Router, private http:Http){
        this.activatedRoute.params.subscribe((prms)=> {
            this.id = prms['carId'];
        });

        this.tid = 0;
        var searchURL = "";

        searchURL = "/accessories/" + this.id;

        var requestHeaders = new Headers({'Accept': 'application/json'});
        var options = new RequestOptions({headers: requestHeaders});

        this.http.get(searchURL, options).subscribe(res => {

            this.accessories = res.json();
            console.log(this.accessories);
        }) ;

        this.incrementor = new Incrementor(0);
        this.incrementorSet = new Set<Incrementor>();
    }


    funcToCust() {
        var checkedIds = "";
        var arrId = [];
        var incr:Incrementor = new Incrementor(0);
        var rows = document.querySelectorAll('tr');
        for(var i = 1; i < rows.length - 1; i++){
            var currentRow = rows[i];
            var checked = currentRow.querySelectorAll('td')[4].querySelector('input').getAttribute('checked') === "checked";
            var currentPrice = currentRow.querySelectorAll('td')[4].querySelectorAll('input')[1].value;
            if(checked){
                checkedIds += currentPrice + ",";
            }
        }
        checkedIds = checkedIds && checkedIds.substr(0, checkedIds.length - 1);

        var checkedIdsArray = checkedIds.split(',');
        for(var ctr=0;ctr<checkedIdsArray.length;ctr++){
            if(!arrId) {
                arrId = [];
            }
            arrId.push(Number(checkedIdsArray[ctr]));
            incr.aid = Number(checkedIdsArray[ctr]);
            this.incrementorSet.add(incr);
            incr = new Incrementor(0);
        }
        console.log(arrId);
        console.log(this.incrementorSet);
        console.log("Inside addTransaction()!!!!");
        if(!this.transaction) {
            this.transaction = new Transaction(0, this.result, this.incrementorSet);
        }
        this.transaction = new Transaction(0, this.result, this.incrementorSet);
        console.log(this.transaction);

        let addUrl = "/addTransaction";

        var requestHeaders = new Headers({'Content-Type': 'application/json'});
        var options = new RequestOptions({headers: requestHeaders});

        //this.http.post(addUrl,this.car,options).subscribe(res => this.successMessage = res.toString());
        this.http.post(addUrl, this.transaction, options).subscribe(
            res => {
                this.tid = res.json();
                this.successMessage = res.toString();
                console.log(res.text());
                this.router.navigate(['customer-detail/', this.id, this.tid, this.result]);
                this.errorMessage=""
            },
            error => {
                this.errorMessage = <any>error;
                this.successMessage = ""
            });


    }

    sum(){
        this.result = 0;
        var rows = document.querySelectorAll('tr');
        for(var i = 1; i < rows.length - 1; i++){
            var currentRow = rows[i];
            var checked = currentRow.querySelectorAll('td')[4].querySelector('input').getAttribute('checked') === "checked";
            var currentPrice = currentRow.querySelectorAll('td')[3].innerText;
            if(checked){
                this.result += Number(currentPrice);
            }
        }

    }

    setChecked(checkbox : any, index: any){
        var selector = '#' + checkbox + index;
        var currentInput = document.querySelector(selector);
        if(currentInput.getAttribute('checked') === "checked"){
            currentInput.setAttribute('checked', "");
        } else{
            currentInput.setAttribute('checked', "checked");
        }
    }
}
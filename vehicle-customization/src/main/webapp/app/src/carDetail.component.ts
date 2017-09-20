import {Component, OnInit} from "@angular/core";
import {ActivatedRoute} from "@angular/router";
import {Http,Headers,RequestOptions} from "@angular/http"
import {Router} from "@angular/router";

@Component({
    templateUrl:'../partials/carDetail.component.html',
    styleUrls:['../css/carDetail.component.css'],
	selector:'car-app'
})

export class CarDetailComponent implements  OnInit{
    header:string = "Enter your car details";
    models: [string];
    trims: [string];
    years: [string];
    makeName: string;
    model:string;
    trim:string;
    year:number;
    id: number;


    constructor(private activatedRoute:ActivatedRoute, private router: Router, private http:Http){
        this.activatedRoute.params.subscribe((prms)=> {
            this.makeName = prms['name'];
        });

        var searchURL = "";

        searchURL = "/cars/" + this.makeName;

        var requestHeaders = new Headers({'Accept': 'application/json'});
        var options = new RequestOptions({headers: requestHeaders});

        this.http.get(searchURL, options).subscribe(res => {

            this.models = res.json();
            console.log(this.models);
        }) ;
    }
    ngOnInit(){}

    getTrim(event:any){
        console.log(event);
        this.model = event;
        console.log(this.model);
        var searchURL = "";

        searchURL = "/cars/" + this.makeName + "/" + this.model;

        var requestHeaders = new Headers({'Accept': 'application/json'});
        var options = new RequestOptions({headers: requestHeaders});

        this.http.get(searchURL, options).subscribe(res => {

            this.trims = res.json();
            console.log(this.trims);
        }) ;
    }
    getYear(event:any){
        console.log(event);
        this.trim = event;
        var searchURL = "";

        searchURL = "/cars/" + this.makeName + "/" + this.model + "/" + this.trim;

        var requestHeaders = new Headers({'Accept': 'application/json'});
        var options = new RequestOptions({headers: requestHeaders});

        this.http.get(searchURL, options).subscribe(res => {

            this.years = res.json();
            console.log(this.years);
        }) ;
    }
    getId(event:any){
        console.log(event);
        this.year = event;
        var searchURL = "";

        searchURL = "/cars/" + this.makeName + "/" + this.model + "/" + this.trim + "/" + this.year;

        var requestHeaders = new Headers({'Accept': 'application/json'});
        var options = new RequestOptions({headers: requestHeaders});

        this.http.get(searchURL, options).subscribe(res => {
            console.log(res.json());
            this.id = res.json();
            console.log(this.id);
        }) ;
    }

    passId(pid:number){
        console.log(pid);
        this.router.navigate(['accessories-detail/', pid]);
    }
}
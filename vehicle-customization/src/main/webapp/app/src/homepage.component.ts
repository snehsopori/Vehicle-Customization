import {Component} from "@angular/core";
import {Router} from "@angular/router";

@Component({
    templateUrl:'../partials/homepage.html',
    styleUrls:['../css/homepage.css'],
	selector:'homepage-app'
})
export class HomepageComponent{

    constructor(private router: Router){
    console.log("homepage created");
}
func(modelName: string) {
        console.log(modelName);
    this.router.navigate(['car-detail/', modelName]);
}}
import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';
import {FormsModule} from '@angular/forms';
import { platformBrowserDynamic } from '@angular/platform-browser-dynamic';

import {configuredRoutes} from "./routes.config";
import {HttpModule} from "@angular/http";

import {CustomerDetailComponent} from './customerDetail.component';
import {CarDetailComponent} from './carDetail.component';
import {HomepageComponent} from './homepage.component';
import {AppComponent} from "./app.component";
import {AccessoriesDetailComponent} from "./accessoriesDetail.component";
import {TransactionDetailComponent} from "./transactionDetail.component";



@NgModule({
	imports:[BrowserModule,HttpModule,FormsModule, configuredRoutes],
	declarations:[CustomerDetailComponent,CarDetailComponent,HomepageComponent,AppComponent,AccessoriesDetailComponent,TransactionDetailComponent],
	bootstrap:[AppComponent]
})
export class AppModule{
}

platformBrowserDynamic().bootstrapModule(AppModule);


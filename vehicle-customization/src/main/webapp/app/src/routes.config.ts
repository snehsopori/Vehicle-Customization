import { Routes,RouterModule } from '@angular/router';
import {CarDetailComponent} from "./carDetail.component";
import {HomepageComponent} from "./homepage.component";
import {CustomerDetailComponent} from "./customerDetail.component";
import {AccessoriesDetailComponent} from "./accessoriesDetail.component";
import {TransactionDetailComponent} from "./transactionDetail.component";

let routes:Routes = [
    {path:'home',component:HomepageComponent},
    {path:'car-detail/:name',component:CarDetailComponent},
    {path:'accessories-detail/:carId',component:AccessoriesDetailComponent},
    {path:'customer-detail/:carId/:tid/:bill',component:CustomerDetailComponent},
    {path:'transaction-detail/:carId/:tid/:bill',component:TransactionDetailComponent},
    {path:'',redirectTo:'/home',pathMatch:'full'}
];

export const configuredRoutes = RouterModule.forRoot(routes);
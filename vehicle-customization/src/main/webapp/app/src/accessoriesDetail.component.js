"use strict";
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
///<reference path="transaction.ts"/>
var core_1 = require("@angular/core");
var http_1 = require("@angular/http");
var transaction_1 = require("./transaction");
var incrementor_1 = require("./incrementor");
var AccessoriesDetailComponent = (function () {
    function AccessoriesDetailComponent(activatedRoute, router, http) {
        var _this = this;
        this.activatedRoute = activatedRoute;
        this.router = router;
        this.http = http;
        this.result = 0;
        this.header = "Accessories Details";
        this.activatedRoute.params.subscribe(function (prms) {
            _this.id = prms['carId'];
        });
        this.tid = 0;
        var searchURL = "";
        searchURL = "/accessories/" + this.id;
        var requestHeaders = new http_1.Headers({ 'Accept': 'application/json' });
        var options = new http_1.RequestOptions({ headers: requestHeaders });
        this.http.get(searchURL, options).subscribe(function (res) {
            _this.accessories = res.json();
            console.log(_this.accessories);
        });
        this.incrementor = new incrementor_1.Incrementor(0);
        this.incrementorSet = new Set();
    }
    AccessoriesDetailComponent.prototype.funcToCust = function () {
        var _this = this;
        var checkedIds = "";
        var arrId = [];
        var incr = new incrementor_1.Incrementor(0);
        var rows = document.querySelectorAll('tr');
        for (var i = 1; i < rows.length - 1; i++) {
            var currentRow = rows[i];
            var checked = currentRow.querySelectorAll('td')[4].querySelector('input').getAttribute('checked') === "checked";
            var currentPrice = currentRow.querySelectorAll('td')[4].querySelectorAll('input')[1].value;
            if (checked) {
                checkedIds += currentPrice + ",";
            }
        }
        checkedIds = checkedIds && checkedIds.substr(0, checkedIds.length - 1);
        var checkedIdsArray = checkedIds.split(',');
        for (var ctr = 0; ctr < checkedIdsArray.length; ctr++) {
            if (!arrId) {
                arrId = [];
            }
            arrId.push(Number(checkedIdsArray[ctr]));
            incr.aid = Number(checkedIdsArray[ctr]);
            this.incrementorSet.add(incr);
            incr = new incrementor_1.Incrementor(0);
        }
        console.log(arrId);
        console.log(this.incrementorSet);
        console.log("Inside addTransaction()!!!!");
        if (!this.transaction) {
            this.transaction = new transaction_1.Transaction(0, this.result, this.incrementorSet);
        }
        this.transaction = new transaction_1.Transaction(0, this.result, this.incrementorSet);
        console.log(this.transaction);
        var addUrl = "/addTransaction";
        var requestHeaders = new http_1.Headers({ 'Content-Type': 'application/json' });
        var options = new http_1.RequestOptions({ headers: requestHeaders });
        //this.http.post(addUrl,this.car,options).subscribe(res => this.successMessage = res.toString());
        this.http.post(addUrl, this.transaction, options).subscribe(function (res) {
            _this.tid = res.json();
            _this.successMessage = res.toString();
            console.log(res.text());
            _this.router.navigate(['customer-detail/', _this.tid, _this.result]);
            _this.errorMessage = "";
        }, function (error) {
            _this.errorMessage = error;
            _this.successMessage = "";
        });
    };
    AccessoriesDetailComponent.prototype.sum = function () {
        this.result = 0;
        var rows = document.querySelectorAll('tr');
        for (var i = 1; i < rows.length - 1; i++) {
            var currentRow = rows[i];
            var checked = currentRow.querySelectorAll('td')[4].querySelector('input').getAttribute('checked') === "checked";
            var currentPrice = currentRow.querySelectorAll('td')[3].innerText;
            if (checked) {
                this.result += Number(currentPrice);
            }
        }
    };
    AccessoriesDetailComponent.prototype.setChecked = function (checkbox, index) {
        var selector = '#' + checkbox + index;
        var currentInput = document.querySelector(selector);
        if (currentInput.getAttribute('checked') === "checked") {
            currentInput.setAttribute('checked', "");
        }
        else {
            currentInput.setAttribute('checked', "checked");
        }
    };
    AccessoriesDetailComponent = __decorate([
        core_1.Component({
            templateUrl: '../partials/accessoriesDetail.component.html',
            styleUrls: ['../css/accessoriesDetail.component.css'],
            selector: 'accessory-app'
        })
    ], AccessoriesDetailComponent);
    return AccessoriesDetailComponent;
}());
exports.AccessoriesDetailComponent = AccessoriesDetailComponent;
